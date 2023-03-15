<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<sql:setDataSource var="myDS" driver="com.mysql.jdbc.Driver"
    url="jdbc:mysql://localhost:3306/product_management" user="root" password="" />

<sql:query var="listProducts" dataSource="${myDS}">
    SELECT * FROM product;
</sql:query>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Danh sách sản phẩm</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>

<body style="background-color: #f8f8f8">

    <div class="container-fluid p-5">
        <div class="row mb-5">
            <div class="col-md-6">
                <h3>Product Management</h3>
            </div>
            <div class="col-md-6 text-right">
                Xin chào <span class="text-danger">
                    <c:if test="${not empty requestScope.username}">
                        <c:out value='${requestScope.username}' />
                    </c:if>
                </span> | <a href="/logout">Logout</a>
            </div>
        </div>
        <div class="row rounded border p-3">
            <div class="col-md-4">
                <h4 class="text-success">Thêm sản phẩm mới</h4>
                <form class="mt-3" method="post">
                    <div class="form-group">
                        <label for="product-name">Tên sản phẩm</label>
                        <input class="form-control" type="text" placeholder="Nhập tên sản phẩm"
                            id="product-name" name="name">
                    </div>
                    <div class="form-group">
                        <label for="price">Giá sản phẩm</label>
                        <input class="form-control" type="number" placeholder="Nhập giá bán" id="price"
                            name="price">
                    </div>
                    <div class="form-group">
                        <c:if test="${not empty requestScope.error}">
                            <div class="alert alert-danger alert-dismissible fade show">
                                <button type="button" class="close" data-dismiss="alert">&times;</button>
                                <c:out value='${requestScope.error}' />
                            </div>
                        </c:if>

                        <c:if test="${not empty requestScope.message}">
                            <div class="alert alert-success alert-dismissible fade show">
                                <button type="button" class="close" data-dismiss="alert">&times;</button>
                                <c:out value='${requestScope.message}' />
                            </div>
                        </c:if>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-success mr-2">Thêm sản phẩm</button>
                    </div>
                </form>
            </div>
            <div class="col-md-8">
                <h4 class="text-success">Danh sách sản phẩm</h4>
                <p>Chọn một sản phẩm cụ thể để xem chi tiết</p>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>STT</th>
                            <th>Tên sản phẩm</th>
                            <th>Giá</th>
                            <th>Thao tác</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="user" items="${listProducts.rows}">
                            <tr>
                                <td class="product-id">
                                    <c:out value="${user.id}" />
                                </td>
                                <td>
                                    <c:out value="${user.name}" />
                                </td>
                                <td>$
                                    <c:out value="${user.price}" />
                                </td>
                                <td>
                                    <button class="delete-btn btn btn-danger">Xóa</button>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel"
            aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Xoá sản phẩm</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        Bạn có chắc chắn muốn xoá sản phẩm này không?
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
                        <button id="accept-btn" type="button" class="btn btn-primary">Đồng ý</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script>
        // Add the following code if you want the name of the file appear on select
        $(".custom-file-input").on("change", function () {
            var fileName = $(this).val().split("\\").pop();
            $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
        });

        $(".delete-btn").click(function () {
            let row = $(this).closest("tr")
            let id = row.find(".product-id").text().trim()
            $("#exampleModal").modal("show")
            $("#accept-btn").click(function () {
                $.ajax({
                    url: "http://localhost:8080/product?id=" + id,
                    type: "DELETE",
                    success: function (response) {
                        let resp = JSON.parse(response)
                        if (resp.status == "success") {
                            alert("Xoá thành công")
                        } else {
                            alert("Xoá thất bại")
                        }
                        $("#exampleModal").modal("hide")
                        location.reload()
                    }
                })
            })
        })


    </script>
</body>

</html>