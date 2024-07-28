<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:include page="admin_header.jsp" />
    
    <div class="container">
        <h1>Edit Product</h1>
        <form action="edit" method="post" enctype="multipart/form-data">
            <input type="hidden" name="id" value="${product.id}">
            <div class="form-field">
                <label for="name">Name:</label>
                <input type="text" id="name" name="name" value="${product.name}" required>
            </div>
            <div class="form-field">
                <label for="image">Image:</label>
                <input type="file" id="image" name="image">
                <p>Current Image: ${product.image}</p>
            </div>
            <div class="form-field">
                <label for="price">Price:</label>
                <input type="number" id="price" name="price" value="${product.price}" min="0" step="0.01" required>
                
            </div>
            <div class="form-field">
                <label for="description">Description:</label><br>
                <textarea id="description" name="description" rows="4" required>${product.description}</textarea>
            </div>
            <div class="form-field">
                <input type="submit" value="Save Changes">
            </div>
        </form>
    </div>
   
    <jsp:include page="admin_footer.jsp" />
