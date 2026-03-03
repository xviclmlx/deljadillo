let modalDivision;

document.addEventListener("DOMContentLoaded", function () {

    modalDivision = new bootstrap.Modal(document.getElementById('modalDivision'));

    const btnNueva = document.getElementById("btnNuevaDivision");
    if (btnNueva) btnNueva.addEventListener("click", abrirModalNueva);

    // Delegación para botones editar
    document.addEventListener("click", function (e) {
        if (e.target.classList.contains("btn-editar")) {
            const id = e.target.getAttribute("data-id");
            editarDivision(id);
        }
    });

    // Interceptar submit del formulario
    const form = document.getElementById("formDivision");
    form.addEventListener("submit", function (e) {
        e.preventDefault();
        guardarDivision();
    });

});

function limpiarFormulario() {
    document.getElementById("divisionId").value = "";
    document.getElementById("claveInput").value = "";
    document.getElementById("nombreInput").value = "";
    document.getElementById("activoInput").checked = false;
}

function abrirModalNueva() {
    document.getElementById("tituloModal").innerText = "Nueva División";
    limpiarFormulario();
    modalDivision.show();
}

function editarDivision(id) {

    fetch(`/consola/divisiones/${id}`)
        .then(r => r.json())
        .then(data => {

            document.getElementById("tituloModal").innerText = "Editar División";

            document.getElementById("divisionId").value = data.id;
            document.getElementById("claveInput").value = data.clave;
            document.getElementById("nombreInput").value = data.nombre;
            document.getElementById("activoInput").checked = data.activo;

            modalDivision.show();
        })
        .catch(() => alert("Error al cargar división"));
}

function guardarDivision() {

    const division = {
        id: document.getElementById("divisionId").value || null,
        clave: document.getElementById("claveInput").value,
        nombre: document.getElementById("nombreInput").value,
        activo: document.getElementById("activoInput").checked
    };

    fetch("/consola/divisiones/update", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(division)
    })
        .then(r => r.json())
        .then(data => {

            actualizarFilaDOM(data);

            modalDivision.hide();
        })
        .catch(() => alert("Error al guardar división"));
}

function actualizarFilaDOM(division) {

    let fila = document.getElementById(`fila-${division.id}`);

    // Si no existe, es nueva → agregar fila
    if (!fila) {
        const tbody = document.querySelector("tbody");

        fila = document.createElement("tr");
        fila.id = `fila-${division.id}`;

        fila.innerHTML = `
            <td>${division.id}</td>
            <td>${division.clave}</td>
            <td>${division.nombre}</td>
            <td>${division.activo ? "Sí" : "No"}</td>
            <td>
                <button class="btn btn-warning btn-sm btn-editar" data-id="${division.id}">
                    Editar
                </button>
            </td>
        `;

        tbody.appendChild(fila);
        return;
    }

    // Si existe, actualizar datos
    const celdas = fila.children;
    celdas[1].innerText = division.clave;
    celdas[2].innerText = division.nombre;
    celdas[3].innerText = division.activo ? "Sí" : "No";
}
