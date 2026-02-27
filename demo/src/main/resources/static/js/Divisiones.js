let modalDivision;

document.addEventListener("DOMContentLoaded", function () {

    // Inicializar modal
    modalDivision = new bootstrap.Modal(document.getElementById('modalDivision'));

    // Botón nueva división
    const btnNueva = document.getElementById("btnNuevaDivision");
    if (btnNueva) {
        btnNueva.addEventListener("click", abrirModalNueva);
    }

    // Delegación para botones editar
    document.addEventListener("click", function (e) {

        if (e.target.classList.contains("btn-editar")) {
            const id = e.target.getAttribute("data-id");
            editarDivision(id);
        }

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
        .then(response => {
            if (!response.ok) {
                throw new Error("Error al obtener división");
            }
            return response.json();
        })
        .then(data => {

            document.getElementById("tituloModal").innerText = "Editar División";

            document.getElementById("divisionId").value = data.id;
            document.getElementById("claveInput").value = data.clave;
            document.getElementById("nombreInput").value = data.nombre;
            document.getElementById("activoInput").checked = data.activo;

            modalDivision.show();
        })
        .catch(error => {
            console.error("Error:", error);
            alert("No se pudo cargar la división");
        });

}