// --- Configuración fácil de editar ---
const EMOJIS = ["😄", "😊", "🙂", "😐", "😔", "😢", "😡", "😤", "😴", "🤒", "😱", "🥳"];
const STORAGE_KEY = "consejos_entries_v1";
const MAX_CHARS = 200;

// --- Elementos del DOM ---
const emojiGrid = document.getElementById("emoji-grid");
const emojiPrev = document.getElementById("emoji-preview");
const textArea = document.getElementById("mood-text");
const saveBtn = document.getElementById("save-btn");
const charCount = document.getElementById("char-count");
const entriesWrap = document.getElementById("entries");

let selectedEmoji = null;

// --- LocalStorage ---
const loadEntries = () => JSON.parse(localStorage.getItem(STORAGE_KEY) || "[]");
const saveEntries = (arr) => localStorage.setItem(STORAGE_KEY, JSON.stringify(arr));

// --- Render de la grilla de emojis ---
function renderEmojis() {
    emojiGrid.innerHTML = "";
    EMOJIS.forEach((e) => {
        const btn = document.createElement("button");
        btn.type = "button";
        btn.className = "emoji-btn";
        btn.textContent = e;
        btn.onclick = () => {
            // limpiar selección previa
            emojiGrid.querySelectorAll(".emoji-btn.selected").forEach(b => b.classList.remove("selected"));
            btn.classList.add("selected");
            selectedEmoji = e;
            emojiPrev.textContent = e; // mostrar en la pill
        };
        emojiGrid.appendChild(btn);
    });
}

// --- Guardar registro (emoji + texto) ---
function handleSave() {
    const text = textArea.value.trim();
    if (!selectedEmoji)
        return alert("Elige un emoji primero.");
    if (!text)
        return alert("Escribe cómo te sientes.");

    const entries = loadEntries();
    entries.unshift({
        id: crypto.randomUUID(),
        emoji: selectedEmoji,
        text,
        ts: new Date().toISOString()
    });
    saveEntries(entries);
    textArea.value = "";
    charCount.textContent = `0 / ${MAX_CHARS}`;
    renderEntries();
}

// --- Render de la lista ---
function renderEntries() {
    const entries = loadEntries();
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
        tx.textContent = item.text;

        const right = document.createElement("div");
        right.className = "text-end d-flex flex-column gap-2";
        
        // Fecha y Hora
        const meta = document.createElement("div");
        meta.className = "entry-meta";
        const d = new Date(item.ts);
        const dateLine = d.toLocaleDateString(undefined, {day: '2-digit', month: '2-digit', year: 'numeric'});
        const timeLine = d.toLocaleTimeString(undefined, {hour: '2-digit', minute: '2-digit', second: '2-digit'});
        meta.innerHTML = `<span>${dateLine}</span><span>${timeLine}</span>`;

        const del = document.createElement("button");
        del.type = "button";
        del.className = "btn btn-outline-danger btn-sm";
        del.innerHTML = `<i class="bi bi-trash"></i>`;
        del.title = "Eliminar";
        del.onclick = () => {
            const filtered = loadEntries().filter(x => x.id !== item.id);
            saveEntries(filtered);
            renderEntries();
        };

        right.append(meta, del);
        row.append(em, tx, right);
        entriesWrap.appendChild(row);
    });
}

// --- Eventos básicos ---
textArea.addEventListener("input", () => {
    charCount.textContent = `${textArea.value.length} / ${MAX_CHARS}`;
});
saveBtn.addEventListener("click", handleSave);

// --- Inicio ---
renderEmojis();
renderEntries();

