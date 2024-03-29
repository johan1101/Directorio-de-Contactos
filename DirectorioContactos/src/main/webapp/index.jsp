<%@page import="Clases.Serializacion"%>
<%@page import="Clases.Directorio"%>
<!-- Incluci�n de la plantilla de header -->
<%@include file= "templates/header.jsp" %>
 

<style>
    <%@include file= "styles/index.css" %>
</style>

<%
    // Creamos una instancia de la clase Directorio para almacenar la lista de contactos
    Directorio listaContactos = new Directorio();

    // Obtener el contexto del servlet
    ServletContext context = getServletContext();

    // Llenamos la lista de contactos con los datos almacenados en el archivo .ser
    listaContactos = Serializacion.leerArchivoContactos(context);

    // Generamos una tabla HTML que muestra la lista de contactos
    String tablaHTML = listaContactos.mostrarArbol(listaContactos, context);

    // Obtener el valor almacenado en la sesi�n con la clave "repetido"
    String valor = (String) session.getAttribute("repetido");

    // Variable para almacenar el valor repetidoParam
    String repetidoParam = "";

    // Si el valor obtenido de la sesi�n es nulo, se asigna "otro" a repetidoParam
    // y se actualiza el valor de repetidoParam en la sesi�n
    if (valor == null) {
        valor = "otro";
        repetidoParam = valor;
    } else {
        // Si el valor no es nulo, se asigna el valor de la sesi�n a repetidoParam
        // y se elimina el atributo de sesi�n "nombreVariable"
        repetidoParam = valor;
        session.removeAttribute("nombreVariable");
    }

    // Verificar si repetidoParam es nulo o diferente de "si" y "no"
    if (repetidoParam == null || (!repetidoParam.equals("si") && !repetidoParam.equals("no"))) {
        repetidoParam = "otro";
    }

    // Imprimir el valor de repetidoParam en la consola del servidor
    System.out.println(repetidoParam);

    // En caso de que se registre exitosamente
    if (repetidoParam != null && repetidoParam.equals("si")) {
%>
<!-- Llama a un m�todo de JavaScript para mostrar una modal de registro exitoso -->
<script>
    $(document).ready(function () {
        usuarioNoR();
    });
</script>
<%
    } // En caso de que no se registre exitosamente
    else if (repetidoParam != null && repetidoParam.equals("no")) {
%>
<!-- Llama a un m�todo de JavaScript para mostrar una modal de registro no exitoso -->
<script>
    $(document).ready(function () {
        usuarioR();
    });
</script>
<%
    }

    // Asigna el valor "otro" a repetidoParam
    repetidoParam = "otro";

    // Elimina el atributo de solicitud "repetido"
    request.removeAttribute("repetido");
%>

<!-- Page Wrapper -->
<div id="wrapper">
    <!-- Sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">
        <!-- Sidebar - Brand -->
        <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.jsp">
            <div class="sidebar-brand-icon rotate-n-15">
                <i class="fas fa-phone"></i>
            </div>
            <div class="sidebar-brand-text mx-3">Directorio</div>
        </a>
        <!-- Divider -->
        <hr class="sidebar-divider my-0">
        <!-- Divider -->
        <hr class="sidebar-divider">
        <!-- Nav Item - Tables -->
        <li class="nav-item active">
            <a class="nav-link" href="index.jsp">
                <i class="fas fa-fw fa-table"></i>
                <span>Directorio de Contactos</span></a>
            <a class="nav-link" href="#" type="button" id="show-login" data-bs-toggle="modal" data-bs-target="#registrar">
                <i class="fas fa-fw fa-user"></i>
                <span>Agregar contacto</span>
            </a>
        </li>
        <!-- Divider -->
        <hr class="sidebar-divider d-none d-md-block">
        <!-- Sidebar Toggler (Sidebar) -->
        <div class="text-center d-none d-md-inline">
            <button class="rounded-circle border-0" id="sidebarToggle"></button>
        </div>
    </ul>
    <!-- End of Sidebar -->

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">
        <!-- Main Content -->
        <div id="content">
            <!-- Topbar -->
            <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
                <!-- Sidebar Toggle (Topbar) -->
                <form class="form-inline">
                    <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                        <i class="fa fa-bars"></i>
                    </button>
                </form>

                <!-- Topbar Navbar -->
                <ul class="navbar-nav ml-auto">

                    <!-- Nav Item - Search Dropdown (Visible Only XS) -->
                    <li class="nav-item dropdown no-arrow d-sm-none">
                        <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="fas fa-search fa-fw"></i>
                        </a>
                        <!-- Dropdown - Messages -->
                        <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in"
                             aria-labelledby="searchDropdown">
                            <form class="form-inline mr-auto w-100 navbar-search">
                                <div class="input-group">
                                    <input type="text" class="form-control bg-light border-0 small"
                                           placeholder="Search for..." aria-label="Search"
                                           aria-describedby="basic-addon2">
                                    <div class="input-group-append">
                                        <button class="btn btn-primary" type="button">
                                            <i class="fas fa-search fa-sm"></i>
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </li>

                    <div class="topbar-divider d-none d-sm-block"></div>

                    <!-- Nav Item - User Information -->
                    <li class="nav-item dropdown no-arrow">
                        <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <span class="mr-3 d-none d-lg-inline text-gray-600 small ">Usuario</span>
                            <img class="img-profile rounded-circle"
                                 src="img/user3.jpg">
                        </a>
                        <!-- Dropdown - User Information -->
                        <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                             aria-labelledby="userDropdown">
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                                <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                Logout
                            </a>
                        </div>
                    </li>

                </ul>

            </nav>
            <!-- End of Topbar -->

            <!-- Begin Page Content -->
            <div class="container-fluid">

                <!-- DataTales Example -->
                <div class="card shadow mb-4">
                    <div class="card-header py-3">
                        <h6 class="m-0 font-weight-bold text-primary">Directorio de contactos</h6>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                <thead>
                                    <tr>
                                        <th>Id</th>
                                        <th>Nombres</th>
                                        <th>Apellidos</th>
                                        <th>Telefono</th>
                                        <th>Direcci�n</th>
                                        <th>Correo</th>
                                        <th>Acciones</th>
                                    </tr>
                                </thead>
                                <tbody>             
                                    <%= tablaHTML%>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /.container-fluid -->
        </div>
        <!-- End of Main Content -->

        <!-- Footer -->
        <footer class="sticky-footer bg-white">
            <div class="container my-auto">
                <div class="copyright text-center my-auto">
                    <span>Copyright &copy; Your Website 2020</span>
                </div>
            </div>
        </footer>
        <!-- End of Footer -->

    </div>
    <!-- End of Content Wrapper -->

</div>
<!-- End of Page Wrapper -->

<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
</a>

<form action="SvAgregarContacto" method="POST">
    <div class="modal fade" id="registrar" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="registrarLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered custom-modal-size">
            <div class="modal-content">
                <div class="popup">
                    <div class="close-btn btn-close" data-bs-dismiss="modal">&times;</div>
                    <div class="form">
                        <h2>Agregar contacto</h2>
                        <hr>
                        <div class="row">
                            <div class="col">
                                <div class="form-element">
                                    <label for="nombre">Nombre</label>
                                    <input type="text" id="nombre" name="nombre" placeholder="Ingresa el nombre" maxlength="20" required pattern="[a-zA-Z������������\s]+" title="No se permiten n�meros">
                                </div>
                            </div>
                            <div class="col">
                                <div class="form-element">
                                    <label for="apellido">Apellido</label>
                                    <input type="text" id="apellido" name="apellido" placeholder="Ingresa el apellido" maxlength="20" required pattern="[a-zA-Z������������\s]+" title="No se permiten n�meros">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="form-element">
                                    <label for="celular">Celular</label>
                                    <input type="text" id="celular" name="celular" placeholder="Ingresa su n�mero celular" maxlength="10" required pattern="[0-9]+" title="Solo se permiten n�meros">
                                </div>
                            </div>
                            <div class="col">
                                <div class="form-element">
                                    <label for="direccion">Direcci�n</label>
                                    <input type="text" id="direccion" name="direccion" placeholder="Ingresa su direcci�n" maxlength="40" required>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="form-element">
                                    <label for="correo">Correo electr�nico</label>
                                    <input type="email" id="correo" name="correo" placeholder="Ingresa el correo electr�nico" maxlength="40" required>
                                </div>

                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="form-element">
                                    <button type="submit">Registrar</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>


<div class="modal fade" id="visualizar" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="visualizarLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered custom-modal-size">
        <div class="modal-content">
            <div class="popup">
                <div class="close-btn btn-close" data-bs-dismiss="modal">&times;</div>
                <div class="form">
                    <div id="user-details">

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="eliminar" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="eliminarLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="popup">
                <div class="close-btn btn-close" data-bs-dismiss="modal">&times;</div>
                <div class="form">
                    <div id="user-details">
                        <h2>�Seguro que quieres eliminar este contacto?</h2>
                        <div class="form-element" style="display: flex; justify-content: space-between;">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" style="margin-right: 10px;">Cancelar</button>
                            <button type="button" class="btn btn-danger" style="margin-left: 10px;" onclick="eliminar()">Eliminar</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


    <div class="modal fade" id="editar" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="editarLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered custom-modal-size">
            <div class="modal-content">
                <div class="popup">
                    <div class="close-btn btn-close" data-bs-dismiss="modal">&times;</div>
                    <div class="form">
                        <div id="user-edit">    

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>



<!-- Bootstrap core JavaScript-->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="js/sb-admin-2.min.js"></script>

<!-- Page level plugins -->
<script src="vendor/datatables/jquery.dataTables.min.js"></script>
<script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>

<!-- Page level custom scripts -->
<script src="js/demo/datatables-demo.js"></script>

<script>

    // Funci�n para abrir el modal de edici�n y realizar una solicitud AJAX para obtener los detalles del usuario a editar
    function editar(codigo) {
        var id = codigo;
        $('#editar').modal('show'); // Mostrar el modal de edici�n

        // Realizar una solicitud AJAX para obtener los detalles del usuario a editar
        $.ajax({
            url: 'SvEditar?codigo=' + id, // URL del servlet que maneja la solicitud de edici�n
            method: 'GET', // M�todo HTTP utilizado (en este caso, GET)
            success: function (data) {
                $('#user-edit').html(data); // Mostrar los detalles del usuario en el modal de edici�n
            },
            error: function () {
                console.log('Error al realizar la solicitud de edici�n.'); // Registrar un mensaje de error en la consola en caso de error
            }
        });
    }

    // Funci�n para abrir el modal de visualizaci�n y realizar una solicitud AJAX para obtener los detalles del usuario
    function visualizar(codigo) {
        var id = codigo;
        $('#visualizar').modal('show'); // Mostrar el modal de visualizaci�n

        // Realizar una solicitud AJAX para obtener los detalles del usuario
        $.ajax({
            url: 'SvAgregarContacto?codigo=' + id, // URL del servlet que maneja la solicitud de visualizaci�n
            method: 'GET', // M�todo HTTP utilizado (en este caso, GET)
            success: function (data) {
                $('#user-details').html(data); // Mostrar los detalles del usuario en el modal de visualizaci�n
            },
            error: function () {
                console.log('Error al realizar la solicitud de visualizaci�n.'); // Registrar un mensaje de error en la consola en caso de error
            }
        });
    }

    // Funci�n para mostrar el modal de confirmaci�n de eliminaci�n y almacenar el nombre del usuario a eliminar
    var nombreEliminar;
    function modalEliminar(id) {
        $('#eliminar').modal('show'); // Mostrar el modal de confirmaci�n de eliminaci�n
        nombreEliminar = id; // Almacenar el nombre del usuario a eliminar
    }

    // Funci�n para eliminar un usuario mediante una solicitud AJAX
    function eliminar() {
        $('#eliminar').modal('hide'); // Ocultar el modal de confirmaci�n de eliminaci�n
        var idEliminado = nombreEliminar; // Obtener el nombre del usuario a eliminar
        $.ajax({
            url: 'SvEliminar?id=' + idEliminado, // URL del servlet que maneja la solicitud de eliminaci�n
            method: 'POST', // M�todo HTTP utilizado (en este caso, POST)
            success: function (data) {
                location.reload(); // Recargar la p�gina despu�s de la eliminaci�n exitosa del usuario
            },
            error: function () {
                console.log('Error al realizar la solicitud de eliminaci�n.'); // Registrar un mensaje de error en la consola en caso de error
            }
        });
    }

    // Funci�n para mostrar una notificaci�n Toastr de error cuando el registro no fue exitoso
    function usuarioNoR() {
        // Configurar opciones Toastr
        toastr.options = {
            "closeButton": false,
            "debug": false,
            "newestOnTop": false,
            "progressBar": false,
            "positionClass": "toast-top-center",
            "preventDuplicates": false,
            "onclick": null,
            "showDuration": "300",
            "hideDuration": "1000",
            "timeOut": "5000",
            "extendedTimeOut": "1000",
            "showEasing": "swing",
            "hideEasing": "linear",
            "showMethod": "fadeIn",
            "hideMethod": "fadeOut"
        };

        // Mostrar una notificaci�n Toastr de error
        toastr.error('Ya existe un contacto registrado con ese nombre', 'Error');
    }

    // Funci�n para mostrar una notificaci�n Toastr de �xito cuando el registro fue exitoso
    function usuarioR() {
        // Configurar opciones Toastr
        toastr.options = {
            "closeButton": false,
            "debug": false,
            "newestOnTop": false,
            "progressBar": false,
            "positionClass": "toast-top-center",
            "preventDuplicates": false,
            "onclick": null,
            "showDuration": "300",
            "hideDuration": "1000",
            "timeOut": "5000",
            "extendedTimeOut": "1000",
            "showEasing": "swing",
            "hideEasing": "linear",
            "showMethod": "fadeIn",
            "hideMethod": "fadeOut"
        };

        // Mostrar una notificaci�n Toastr de �xito
        toastr.success('Se ha registrado exitosamente!', 'Registrado');
    }
</script>

<!-- Incluci�n de la plantilla de footer -->
<%@include file= "templates/footer.jsp" %>
