// --- Configuración fácil de editar ---
const EMOJIS = ["😄", "😊", "🙂", "😐", "😔", "😢", "😡", "😤", "😴", "🤒", "😱", "🥳"];
const MAX_CHARS = 200;

// mapa emoji -> code (debe coincidir con la tabla Emocion)
const EMOJI_TO_CODE = {
  "😄": "muy_feliz",
  "😊": "feliz",
  "🙂": "contento",
  "😐": "neutral",
  "😔": "desanimado",
  "😢": "triste",
  "😡": "enojado",
  "😤": "frustrado",
  "😴": "somnoliento",
  "🤒": "enfermo",
  "😱": "sorprendido",
  "🥳": "fiesta",
};

// --- Elementos del DOM ---
const emojiGrid = document.getElementById("emoji-grid");
const emojiPrev = document.getElementById("emoji-preview");
const textArea = document.getElementById("mood-text");
const saveBtn = document.getElementById("save-btn");
const charCount = document.getElementById("char-count");
const entriesWrap = document.getElementById("entries");

let selectedEmoji = null;

// --- API Backend ---
async function apiListarEstados() {
  const res = await fetch(`/api/estados`);
  if (res.status === 401) {
    window.location.href = "/login";
    return [];
  }
  if (!res.ok) throw new Error("No se pudo listar estados");
  return res.json(); // [{id, emoji, emocion, texto, ts}, ...]
}

async function apiCrearEstado({ emoji, texto }) {
  const code = EMOJI_TO_CODE[emoji];
  const res = await fetch("/api/estados", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ code, texto }),
  });
  if (!res.ok) throw new Error("No se pudo guardar el estado");
  return res.json(); // id generado
}

async function apiEliminarEstado(id) {
  const res = await fetch(`/api/estados/${id}`, { method: "DELETE" });
  if (!res.ok) throw new Error("No se pudo eliminar el estado");
}

// --- Render de la grilla de emojis ---
function renderEmojis() {
  emojiGrid.innerHTML = "";
  EMOJIS.forEach((emoji) => {
    const btn = document.createElement("button");
    btn.type = "button";
    btn.className = "emoji-btn";
    btn.textContent = emoji;
    // ✅ FIX: no usar 'e' (evento) como el emoji; usamos 'emoji'
    btn.addEventListener("click", () => {
      emojiGrid.querySelectorAll(".emoji-btn.selected").forEach((b) => b.classList.remove("selected"));
      btn.classList.add("selected");
      selectedEmoji = emoji;
      emojiPrev.textContent = emoji; // mostrar en la pill
    });
    emojiGrid.appendChild(btn);
  });
}

// --- Guardar registro (emoji + texto) ---
async function handleSave() {
  const text = textArea.value.trim();
  if (!selectedEmoji) return alert("Elige un emoji primero.");
  if (!text) return alert("Escribe cómo te sientes.");
  if (text.length > MAX_CHARS) return alert(`Máximo ${MAX_CHARS} caracteres.`);

  try {
    await apiCrearEstado({ emoji: selectedEmoji, texto: text });
    textArea.value = "";
    charCount.textContent = `0 / ${MAX_CHARS}`;
    await renderEntries(); // recarga desde servidor
  } catch (err) {
    console.error(err);
    alert("Ocurrió un error guardando el estado.");
  }
}

// --- Render de la lista (desde servidor) ---
async function renderEntries() {
  try {
    const entries = await apiListarEstados();

    if (!entries.length) {
      entriesWrap.innerHTML = `<div class="empty">Aún no hay registros</div>`;
      return;
    }

    entriesWrap.innerHTML = "";
    entries.forEach((item) => {
      const row = document.createElement("div");
      row.className = "entry";

      const em = document.createElement("div");
      em.className = "entry-emoji";
      em.textContent = item.emoji;

      const tx = document.createElement("p");
      tx.className = "entry-text";
      tx.textContent = item.texto;

      const right = document.createElement("div");
      right.className = "text-end d-flex flex-column gap-2";

      // Fecha y Hora
      const meta = document.createElement("div");
      meta.className = "entry-meta";
      const d = new Date(item.ts);
      const dateLine = d.toLocaleDateString(undefined, { day: "2-digit", month: "2-digit", year: "numeric" });
      const timeLine = d.toLocaleTimeString(undefined, { hour: "2-digit", minute: "2-digit" });
      meta.innerHTML = `<span>${dateLine}</span><span>${timeLine}</span>`;

      const del = document.createElement("button");
      del.type = "button";
      del.className = "btn btn-outline-danger btn-sm";
      del.innerHTML = `<i class="bi bi-trash"></i>`;
      del.title = "Eliminar";
      del.onclick = async () => {
        try {
          await apiEliminarEstado(item.id);
          await renderEntries();
        } catch (err) {
          console.error(err);
          alert("No se pudo eliminar.");
        }
      };

      right.append(meta, del);
      row.append(em, tx, right);
      entriesWrap.appendChild(row);
    });
  } catch (err) {
    console.error(err);
    entriesWrap.innerHTML = `<div class="empty">No se pudo cargar la lista</div>`;
  }
}

// --- Eventos básicos ---
textArea.addEventListener("input", () => {
  charCount.textContent = `${textArea.value.length} / ${MAX_CHARS}`;
});
saveBtn.addEventListener("click", () => { handleSave(); });

// --- Inicio ---
renderEmojis();
renderEntries();
