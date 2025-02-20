$(document).ready(function () {
  // Load all items
  $("#item-getAll-btn").click(function (event) {
    event.preventDefault(); // Prevent form submission
    console.log("Fetching all items...");

    $.ajax({
      url: `http://localhost:8080/api/v1/item/getAll`,
      method: "GET",
      dataType: "json",
      success: function (response) {
        const tableBody = $("#item-table-body tbody");
        tableBody.empty();

        response.forEach(item => {
          tableBody.append(`
                        <tr>
                            <td>${item.id}</td>
                            <td>${item.name}</td>
                            <td>${item.price}</td>
                            <td>${item.qtyOnHand}</td>
                            <td>
                                <button class="btn btn-warning btn-sm edit-btn"
                                data-id="${item.id}"
                                 data-name="${item.name}"
                                  data-price="${item.price}"
                                   data-qtyonhand="${item.qtyOnHand}">Edit</button>
                                <button class="btn btn-danger btn-sm delete-btn" data-id="${item.id}">Delete</button>
                            </td>
                        </tr>
                    `);
        });
      },
      error: function (err) {
        console.error("Error fetching items:", err);
        alert("Failed to load items.");
      }
    });
  });

  // Save Item
  $("#item-save-btn").click(function (event) {
    event.preventDefault();
    const itemData = {
      id: $("#id").val().trim(),
      name: $("#name").val().trim(),
      price: $("#price").val().trim(),
      qtyOnHand: $("#qtyOnHand").val().trim()
    };

    if (!itemData.id || !itemData.name || !itemData.price || !itemData.qtyOnHand) {
      alert("All fields are required.");
      return;
    }

    $.ajax({
      url: `http://localhost:8080/api/v1/item/save`,
      method: "POST",
      contentType: "application/json",
      data: JSON.stringify(itemData),
      success: function () {
        alert("Item saved successfully!");
        $("#item-getAll-btn").click(); // Refresh list
      },
      error: function (err) {
        console.error("Error saving item:", err);
        alert("Failed to save item.");
      }
    });
  });

  // Edit Item
  $("#item-table-body").on("click", ".edit-btn", function () {
    $("#id").val($(this).data("id"));
    $("#name").val($(this).data("name"));
    $("#price").val($(this).data("price"));
    $("#qtyOnHand").val($(this).data("qtyonhand"));

  });

  $("#item-edit-btn").click(function (event) {
    event.preventDefault();
    const itemData = {
      id: $("#id").val().trim(),
      name: $("#name").val().trim(),
      price: $("#price").val().trim(),
      qtyOnHand: $("#qtyOnHand").val().trim()

    };

    if (!itemData.id || !itemData.name || !itemData.price || !itemData.qtyOnHand) {
      alert("All fields are required.");
      return;
    }

    $.ajax({
      url: `http://localhost:8080/api/v1/item/update`,
      method: "PUT",
      contentType: "application/json",
      data: JSON.stringify(itemData),
      success: function () {
        alert("Item updated successfully!");
        $("#getAll-btn").click();
      },
      error: function (err) {
        console.error("Error updating item:", err);
        alert("Failed to update item.");
      }
    });
  });

 /* // Delete Item
  $("#item-delete-btn").click(function (event) {
    event.preventDefault(); // Prevent default form submission

    const itemId = $("#id").val().trim();

    if (!itemId) {
      alert("Item ID is not found. Please enter a valid ID!");
      return; // Stop execution if ID is missing
    }

    //check entered id exists in the table
    let idExists = false;
    $("#item-table-body tbody tr").each(function (){
      const tableId = $(this).find("td:first").text().trim();
      console.log("Table id: ",tableId,"Entered id: ",itemId);
      if(tableId === itemId){
        idExists = true;
        return false;
      }
    });

    if(!idExists){
      alert("Entered ID does not exist in the table..Please enter a valid Id..");
      return;
    }
    if (confirm("Are you sure you want to delete this item..?")){
        $.ajax({
          url: `http://localhost:8080/api/v1/item/delete/${itemId}`,
          method: "DELETE",
          success: function () {
            alert("Item deleted successfully!");
            $("#item-getAll-btn").click(); // Refresh the list
          },
          error: function (err) {
            console.error("Error deleting item:", err);
            alert("Failed to delete item.");
          }
        });
      }
  });

  $(document).on("click", ".delete-btn", function () {
    const itemId = $(this).data("id");

    if (!confirm("Are you sure you want to delete this item?")) return;

    $.ajax({
      url: `http://localhost:8080/api/v1/item/delete/${itemId}`,
      method: "DELETE",
      success: function () {
        alert("Item deleted successfully!");
        $("#getAll-btn").click();
      },
      error: function (err) {
        console.error("Error deleting item:", err);
        alert("Failed to delete item.");
      }
    });
  });
*/
  ///////////////Reusable Ajax Function for "DELETE" /////////////////////
  function ajaxRequest(url,method,data,successMessage,errorMessage){
    $.ajax({
      url: url,
      method: method,
      contentType: "application/json",
      data: data ? JSON.stringify(data) : null,
      success: function (){
        alert(successMessage);
        $("#item-getAll-btn").click();
      },
      error:function(err){
        console.error(errorMessage,err);
        alert(errorMessage);
      }
    });
  }

  $("#item-delete-btn").click(function (event){
    event.preventDefault();
    const itemId = $("#id").val().trim();

    if (!itemId) {
      alert("Item Id is not found..Please enter a valid ID..!");
      return;
    }

    let idExists = false;
    $("#item-table-body tbody tr").each(function () {
      const tableId = $(this).find("td:first").text().trim();
      console.log("Table Id : ", tableId, "Entered Id : ", itemId);

      if (tableId === itemId) {
        idExists = true;
        return false;
      }
    });

    if (!idExists) {
      alert("Entered id does not exist in the table.Please enter a valid Id..");
      return;
    }

    if (confirm("Are you sure you want to delete this item..?")) {
      ajaxRequest(`http://localhost:8080/api/v1/item/delete/${itemId}`, "DELETE", null,
        "Item deleted successfully..!", "Failed to delete this item..");
    }
  });

  $(document).on("click",".delete-btn",function (){
    const itemId = $(this).data("id");

    if (confirm("Are you sure you want to delete this item..?")){
      ajaxRequest(`http://localhost:8080/api/v1/item/delete/${itemId}`, "DELETE", null,
        "Item deleted successfully..!", "Failed to delete this item..");
    }
  })

});

