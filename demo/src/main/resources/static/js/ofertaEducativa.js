let modal = new bootstrap.Modal(document.getElementById('modalOferta'));

document.addEventListener("DOMContentLoaded", cargarOfertas);

function cargarOfertas() {
    fetch("/api/ofertas")
        .then(res => res.json())
        .then(data => {

            let tabla = document.getElementById("tablaOfertas");
            tabla.innerHTML = "";

            data.forEach(oferta => {

                tabla.innerHTML += `
                    <tr>
                        <td>${oferta.id}</td>
                        <td><img src="${oferta.imagen}" style="max-height:60px"></td>
                        <td>${oferta.nombreOferta}</td>
                        <td>${oferta.modalidad || ''}</td>
                        <td>${oferta.division ? oferta.division.nombre : 'Sin división'}</td>
                        <td>
                            <button class="btn btn-warning btn-sm"
                                onclick="editarOferta(${oferta.id}, '${oferta.nombreOferta}', '${oferta.modalidad || ''}', '${oferta.imagen}', ${oferta.division ? oferta.division.id : 'null'})">
                                Editar
                            </button>
                            <button class="btn btn-danger btn-sm"
                                onclick="eliminarOferta(${oferta.id})">
                                Eliminar
                            </button>
                        </td>
                    </tr>
                `;
            });
        });
}

function abrirModalNueva() {
    document.getElementById("id").value = "";
    document.getElementById("nombreOferta").value = "";
    document.getElementById("modalidad").value = "";
    document.getElementById("imagen").value = "";
    document.getElementById("division").value = "";
    modal.show();
}

function editarOferta(id, nombre, modalidad, imagen, divisionId) {
    document.getElementById("id").value = id;
    document.getElementById("nombreOferta").value = nombre;
    document.getElementById("modalidad").value = modalidad;
    document.getElementById("imagen").value = imagen;
    document.getElementById("division").value = divisionId || "";
    modal.show();
}

function guardarOferta() {

    let oferta = {
        id: document.getElementById("id").value || null,
        nombreOferta: document.getElementById("nombreOferta").value,
        modalidad: document.getElementById("modalidad").value,
        imagen: document.getElementById("imagen").value,
        division: document.getElementById("division").value ?
            { id: document.getElementById("division").value } : null
    };

    fetch("/api/ofertas/save", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(oferta)
    })
    .then(res => res.json())
    .then(() => {
        modal.hide();
        cargarOfertas();
    });
}

function eliminarOferta(id) {
    fetch("/api/ofertas/" + id, {
        method: "DELETE"
    }).then(() => cargarOfertas());
}