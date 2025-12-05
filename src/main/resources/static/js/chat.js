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

const respuestasIA = [
  "Hola, soy Zen 🌿. Gracias por escribir, ¿qué te gustaría compartir hoy?",
  "Te leo con calma. ¿Quieres contarme un poco más sobre cómo te sientes?",
  "Estoy aquí para acompañarte. ¿Qué ha sido lo más difícil de tu día?",
  "Gracias por confiar en mí. ¿Te gustaría que pensemos juntos alguna opción?",
  "A veces ayuda hacer una pausa breve y respirar profundo un par de veces. ¿Quieres intentarlo conmigo?",
  "Si tu día ha sido pesado, una buena opción es identificar una sola cosa pequeña que puedas hacer para sentirte un poquito mejor.",
  "Algo que suele ayudar es poner en palabras lo que sientes. No tiene que sonar perfecto, solo auténtico.",
  "Fue un gusto ayudarte hoy, nos vemos luego 😊👋",
];

let indiceRespuesta = 0;

function obtenerRespuestaIA() {
  const texto = respuestasIA[indiceRespuesta];
  indiceRespuesta = (indiceRespuesta + 1) % respuestasIA.length; // vuelve al inicio
  return texto;
}




// ✅ mostrar primer mensaje de Zen al cargar
window.addEventListener('DOMContentLoaded', () => {
  addMsg({
    type: 'ai',
    text: obtenerRespuestaIA()
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
    text: obtenerRespuestaIA()
  });
}, 400);
});



