let modal = new bootstrap.Modal(document.getElementById('modalOferta'));

document.addEventListener("DOMContentLoaded", function () {
    cargarOfertas();

    // 🔥 Interceptar submit del formulario
    const form = document.getElementById("formOferta");
    form.addEventListener("submit", function (e) {
        e.preventDefault();
        guardarOferta();
    });
});

function cargarOfertas() {
    fetch("/consola/ofertas")
        .then(res => res.json())
        .then(data => {
            let tabla = document.getElementById("tablaOfertas");
            tabla.innerHTML = "";
            data.forEach(oferta => agregarFilaDOM(oferta));
        })
        .catch(() => alert("Error al cargar ofertas"));
}

function abrirModalNueva() {
    document.getElementById("id").value = "";
    document.getElementById("nombreOferta").value = "";
    document.getElementById("modalidad").value = "";
    document.getElementById("imagen").value = "";
    document.getElementById("division").value = "";
    modal.show();
}

function editarOferta(id) {
    fetch(`/consola/ofertas/${id}`)
        .then(res => res.json())
        .then(oferta => {
            document.getElementById("id").value = oferta.id;
            document.getElementById("nombreOferta").value = oferta.nombreOferta;
            document.getElementById("modalidad").value = oferta.modalidad;
            document.getElementById("imagen").value = oferta.imagen;
            document.getElementById("division").value = oferta.division ? oferta.division.id : "";
            modal.show();
        })
        .catch(() => alert("Error al cargar oferta"));
}

function guardarOferta() {
    let oferta = {
        id: document.getElementById("id").value || null,
        nombreOferta: document.getElementById("nombreOferta").value,
        modalidad: document.getElementById("modalidad").value,
        imagen: document.getElementById("imagen").value,
        division: document.getElementById("division").value
            ? { id: document.getElementById("division").value }
            : null
    };

    fetch("/consola/ofertas/update", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(oferta)
    })
        .then(res => res.json())
        .then(data => {
            if (oferta.id) {
                actualizarFilaDOM(data);
            } else {
                agregarFilaDOM(data);
            }
            modal.hide();
        })
        .catch(() => alert("Error al guardar oferta"));
}

function eliminarOferta(id) {
    fetch(`/consola/ofertas/${id}`, { method: "DELETE" })
        .then(() => {
            document.getElementById("fila-" + id).remove();
        })
        .catch(() => alert("Error al eliminar oferta"));
}

function agregarFilaDOM(oferta) {
    let tabla = document.getElementById("tablaOfertas");

    let fila = document.createElement("tr");
    fila.id = "fila-" + oferta.id;
    fila.innerHTML = `
        <td>${oferta.id}</td>
        <td><img src="${oferta.imagen}" style="max-height:60px"></td>
        <td>${oferta.nombreOferta}</td>
        <td>${oferta.modalidad || ''}</td>
        <td>${oferta.division ? oferta.division.nombre : 'Sin división'}</td>
        <td>
            <button class="btn btn-warning btn-sm" onclick="editarOferta(${oferta.id})">Editar</button>
            <button class="btn btn-danger btn-sm" onclick="eliminarOferta(${oferta.id})">Eliminar</button>
        </td>
    `;
    tabla.appendChild(fila);
}

function actualizarFilaDOM(oferta) {
    let fila = document.getElementById("fila-" + oferta.id);

    fila.innerHTML = `
        <td>${oferta.id}</td>
        <td><img src="${oferta.imagen}" style="max-height:60px"></td>
        <td>${oferta.nombreOferta}</td>
        <td>${oferta.modalidad || ''}</td>
        <td>${oferta.division ? oferta.division.nombre : 'Sin división'}</td>
        <td>
            <button class="btn btn-warning btn-sm" onclick="editarOferta(${oferta.id})">Editar</button>
            <button class="btn btn-danger btn-sm" onclick="eliminarOferta(${oferta.id})">Eliminar</button>
        </td>
    `;
}
