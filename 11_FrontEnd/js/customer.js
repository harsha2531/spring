//load all customers
$("#getAll-btn").click(function (event){
  event.preventDefault();

  $.ajax({
   url:`http://localhost:8080/10_BackEnd_war_exploded/api/v1/customer/getAll`,
   method: "GET",
    dataType: "json",
    success: function (response){
     const tableBody = $("#customer_table_body tbody");
     tableBody.empty();

     response.forEach(customer => {
       tableBody.append(`
            <tr>
            <td>${customer.id}</td>
            <td>${customer.name}</td>
            <td>${customer.address}</td>
            <td>
            <button class="btn btn-warning btn-sm edit-btn" data-id="${customer.id}"  data-name="${customer.name}" data-address="${customer.address}">Edit</button>
            <button class="btn btn-danger btn-sm delete-btn" data-id="${customer.id}">Delete</button>
</td>
</tr>
       `);
     })
    },
    error: function (err){
     console.error("Error fetching customers: ",err);
     alert("Failed to load customers");
    }
  });
});

//save customer
$("#save-btn").click(function (){
  event.preventDefault();
  const customerData = {
    id:$("#id").val().trim(),
    name:$("#name").val().trim(),
    address:$("#address").val().trim()
  };

  if(!customerData.id || !customerData.name ||!customerData.address){
    alert("All fields are required");
    return;
  }

  $.ajax({
    url: `http://localhost:8080/10_BackEnd_war_exploded/api/v1/customer/save`,
    method: "POST",
    contentType:"application/json",
    data:JSON.stringify(customerData),
    success: function (){
      alert("Customer saved successfully..");
      $("#getAll-btn").click();
    },
    error: function (err){
      console.error("Error saving customer:",err);
      alert("Failed to save customer..!");
    }
  });
});

//edit customer
$(document).on("click",".edit-btn",function (){
  $("#id").val($(this).data("id"));
  $("#name").val($(this).data("name"));
  $("#address").val($(this).data("address"));
});

$("#edit-btn").click(function (event){
  event.preventDefault();
  const customerData = {
    id: $("#id").val().trim(),
    name: $("#name").val().trim(),
    address: $("#address").val().trim()
  };

  if (!customerData.id || !customerData.name || !customerData.address){
    alert("All fields are required..!");
    return;
  }
  $.ajax({
    url: `http://localhost:8080/10_BackEnd_war_exploded/api/v1/customer/update`,
    method: "PUT",
    contentType: "application/json",
    data: JSON.stringify(customerData),
    success:function (){
      alert("Customer updated successfully...");
      $("#getAll-btn").click();
    },
    error: function (err){
      console.error("Error updating customer: ",err);
      alert("Failed to update customer..!");
    }
  });
});

//delete customer
$(document).on("click", ".delete-btn",function (){
  const customerId = $(this).data("id");

  if(!confirm("Are you sure you want to delete this customer..?"))
    return;

  $.ajax({
    url: `http://localhost:8080/10_BackEnd_war_exploded/api/v1/customer/delete/${customerId}`,
    method: "DELETE",
    success: function (){
      alert("Customer deleted successfully...");
      $("#getAll-btn").click();
    },
    error: function (err){
      console.error("Error deleting customer: ",err);
      alert("Failed to delete customer..!");
    }
  });
});

