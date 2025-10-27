
const alerta = document.getElementById('alertError');
if (alerta) {
    setTimeout(() => {
        alerta.classList.add('fade');
        alerta.style.opacity = '0';

        setTimeout(() => alerta.remove(), 500); // se elimina al terminar animación
    }, 3000); // 3 segundos visible
}

