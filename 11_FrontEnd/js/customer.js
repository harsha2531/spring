$(document).ready(function () {
  $("#getAll-btn").click(function () {
    console.log("Get All Customers button clicked");

    $.ajax({
      url: "http://localhost:8080/10_BackEnd_Web_exploded/api/v1/customer/getAll",
      method: "GET",
      dataType: "json",
      success: function (response) {
        const tableBody = $("#customer_table_body");
        tableBody.empty();

        response.forEach(customer => {
          tableBody.append(`
                        <tr>
                            <td>${customer.id}</td>
                            <td>${customer.name}</td>
                            <td>${customer.address}</td>
                            <td>
                                <button class="btn btn-warning btn-sm" onclick="editCustomer('${customer.id}', '${customer.name}', '${customer.address}')">Edit</button>
                                <button class="btn btn-danger btn-sm" onclick="deleteCustomer('${customer.id}')">Delete</button>
                            </td>
                        </tr>
                    `);
        });
      },
      error: function (err) {
        console.error("Error fetching customer data:", err);
        alert("Failed to load customer data. Please try again later.");
      }
    });
  });
});

$("#save-btn").click(function (){

})
$("#edit-btn").click(function (){

})
$("#delete-btn").click(function (){

})
