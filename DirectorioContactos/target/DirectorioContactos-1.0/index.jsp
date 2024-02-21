<%@page import="Clases.Serializacion"%>
<%@page import="Clases.Directorio"%>
<!-- Inclución de la plantilla de header -->
<%@include file= "templates/header.jsp" %>

<style>
    <%@include file= "styles/index.css" %>
</style>

<%
    //Creamos una lista
    Directorio listaContactos = new Directorio();

    // Obtener el contexto del servlet
    ServletContext context = getServletContext();

    //Llenamos la lista con el .ser
    listaContactos = Serializacion.leerArchivoContactos(context);

    //Generamos la tabla
    String tablaHTML = listaContactos.mostrarArbol(listaContactos, context);

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
                            <span class="mr-3 d-none d-lg-inline text-gray-600 small">Douglas McGee</span>
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
                                        <th>Dirección</th>
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
                                    <input type="text" id="nombre" name="nombre" placeholder="Ingresa el nombre" maxlength="20" required pattern="[a-zA-ZáéíóúÁÉÍÓÚñÑ\s]+" title="No se permiten números">
                                </div>
                            </div>
                            <div class="col">
                                <div class="form-element">
                                    <label for="apellido">Apellido</label>
                                    <input type="text" id="apellido" name="apellido" placeholder="Ingresa el apellido" maxlength="20" required pattern="[a-zA-ZáéíóúÁÉÍÓÚñÑ\s]+" title="No se permiten números">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="form-element">
                                    <label for="celular">Celular</label>
                                    <input type="text" id="celular" name="celular" placeholder="Ingresa su número celular" maxlength="10" required pattern="[0-9]+" title="Solo se permiten números">
                                </div>
                            </div>
                            <div class="col">
                                <div class="form-element">
                                    <label for="direccion">Dirección</label>
                                    <input type="text" id="direccion" name="direccion" placeholder="Ingresa su dirección" maxlength="40" required>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="form-element">
                                    <label for="correo">Correo electrónico</label>
                                    <input type="email" id="correo" name="correo" placeholder="Ingresa el correo electrónico" maxlength="40" required>
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
                    <h2>¿Seguro que quieres eliminar este contacto?</h2>
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
    function visualizar(codigo) {
        var id = codigo;
        $('#visualizar').modal('show');

        $.ajax({
            url: 'SvAgregarContacto?codigo=' + id,
            method: 'GET', // Utiliza POST u otro método HTTP según corresponda
            success: function (data) {
                $('#user-details').html(data);
            },
            error: function () {
                // En caso de error en la solicitud:
                // Registra un mensaje de error en la consola (para fines de depuración)
                console.log('Error al realizar la ordenación alfabética.');
            }
        });
    }

var nombreEliminar;

    function modalEliminar(id) {
        $('#eliminar').modal('show');

nombreEliminar = id;

    }

        function eliminar() {
        $('#eliminar').modal('hide');
        var idEliminado = nombreEliminar;
        $.ajax({
            url: 'SvEliminar?id=' + idEliminado,
            method: 'POST', // Utiliza POST u otro método HTTP según corresponda
            success: function (data) {
                location.reload();
            },
            error: function () {
                // En caso de error en la solicitud:
                // Registra un mensaje de error en la consola (para fines de depuración)
                console.log('Error al realizar la ordenación alfabética.');
            }
        });
    }
    
 
     
</script>

<!-- Inclución de la plantilla de footer -->
<%@include file= "templates/footer.jsp" %>
