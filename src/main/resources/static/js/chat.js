const chatBody  = document.getElementById('chat-body');
const chatForm  = document.getElementById('chat-form');
const chatInput = document.getElementById('chat-input');
const msgCounter = document.querySelector('.text-muted'); // 👈 tu <small>

let mensajesUsuario = 0;
const LIMITE_MENSAJES = 15;

// ✅ función para actualizar el texto del contador
function actualizarContador() {
  msgCounter.textContent = `${mensajesUsuario} de ${LIMITE_MENSAJES} Mensajes disponibles`;
}

// ✅ función para agregar mensaje
function addMsg({ text, type }) {
  const msg = document.createElement('div');
  msg.className = `msg ${type}`;
  msg.innerHTML = `
    ${type === 'ai' ? `<img class="avatar" src="/imagenes/Logo-App.png" alt="IA">` : ''}
    <div class="bubble">${text}</div>
    ${type === 'user' ? `<img class="avatar" src="/imagenes/perfil-usuario.png" alt="Yo">` : ''}
  `;
  chatBody.appendChild(msg);
  chatBody.scrollTop = chatBody.scrollHeight;
}

// ✅ mostrar primer mensaje de Zen al cargar
window.addEventListener('DOMContentLoaded', () => {
  addMsg({
    type: 'ai',
    text: `Hola, soy Zen, tu guía de bienestar emocional 🌿.
¿Cómo te sientes hoy o en qué puedo apoyarte?`
  });
  actualizarContador(); // inicializa el texto con 0 de 15
});

// ✅ evento de envío de mensaje
chatForm.addEventListener('submit', (e) => {
  e.preventDefault();
  const txt = chatInput.value.trim();
  if (!txt) return;

  // si llegó al límite, bloquea nuevos mensajes
  if (mensajesUsuario >= LIMITE_MENSAJES) {
    alert("Has alcanzado el límite de mensajes disponibles.");
    chatInput.disabled = true;
    return;
  }

  addMsg({ text: txt, type: 'user' });
  mensajesUsuario++;
  actualizarContador();
  chatInput.value = '';

  // respuesta automática de Zen
  setTimeout(() => {
    addMsg({
      type: 'ai',
      text: `Hola, soy Zen, tu guía de bienestar emocional 🌿.
¿Cómo te sientes hoy o en qué puedo apoyarte?`
    });
  }, 400);
});



