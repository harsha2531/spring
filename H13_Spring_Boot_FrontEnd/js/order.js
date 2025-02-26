$(document).ready(function (){
  let orderList = [];

  //Generate order id and date
  function generateOrderDetails(){
    let nextId = 1;
    if (order_array.length > 0) {
      nextId = Math.max(...order_array.map(o => parseInt(o.id.slice(1)))) + 1;
    }
    $("#order-id").text(`${nextId}`);
    $("#order-date").text(new Date().toLocaleDateString());
  }

  //load customers
  function loadCustomers(){
    $.ajax({
      url: "http://localhost:8080/api/v1/customer/getAll",
      method: "GET",
      success:function (customers){
        $("#customer-select").empty().append(`<option value="">Select Customer</option>`);
        customers.forEach(customer => {
          $("#customer-select").append(`<option value="${customer.id}">${customer.name}</option>`);
        });
      },
      error:function (){
        alert("Failed to load customers..!");
      }
    });
  }

  //load items
  function loadItems(){
    $.ajax({
      url: "http://localhost:8080/api/v1/item/getAll",
      method: "GET",
      success:function (items){
          $("#item-select").empty().append(`<option value="">Select Item</option>`);
          items.forEach(item => {
            $("#item-select").append(`<option value="${item.id}" data-price="${item.price}">${item.name}</option>`);
          });
      },
      error:function (){
        alert("Failed to load items..!");
      }
    });
  }

  $("#item-select").change(function (){
    let selectedItem = $(this).find(":selected");
    $("#unitPrice").val(selectedItem.data("price") || "");
  });

  //calculate total price
  $("#quantity").on("input",function (){
    let qty = parseInt($(this).val());
    let unitPrice = parseFloat($("#unitPrice").val());

    if (!isNaN(qty) && qty > 0 && unitPrice ){
      $("#totalPrice").val((unitPrice * qty).toFixed(2));
    }else {
      $("#totalPrice").val("");
    }
  });

  //place order
  $("#order-form").submit(function (event){
    event.preventDefault();

    let orderId = $("#order-id").text();
    let customerId = $("#customer-select").val();
    let orderDate = $("#order-date").text();
    let itemId = $("#item-select").val();
    let qty = $("#quantity").val();
    let totalPrice = $("#totalPrice").val();

    if (!customerId || !itemId || !qty){
      alert("Please fill in all fields..!");
      return;
    }

    let orderData = {
      id: orderId,
      date: orderDate,
      customer:{id:parseInt(customerId)},
      orderDetails:[
        {
          item:{id:parseInt(itemId)},
          qty:parseInt(qty),
          totalPrice:parseFloat(totalPrice)
        }
        ]
    }
  });

  $.ajax({
    url:"http://localhost:8080/api/v1/order/place",
    method:"POST",
    contentType: "application/json",
    data: JSON.stringify(orderData),
    success:function (response){
      alert("Order placed successfully..!");
      generateOrderDetails();
    },
    error:function(){
      alert("Failed to place order..");
    }
  });

  function fetchOrders(){
    $.ajax({
      url:"http://localhost:8080/api/v1/order",
      method:"GET",
      success:function (orders){
        let tableBody = $("#order-table-body");
        tableBody.empty();

        orders.forEach(order => {
          order.orderDetails.forEach(detail =>{
            tableBody.append(`
                <tr>
                <td>${order.id}</td>
                <td>${order.customer.id}</td>
                <td>${detail.item.id}</td>
                <td>${detail.qty}</td>
                <td>${detail.totalPrice}</td>
                <td><button class="btn btn-danger btn-sm delete-order" data-id="${order.id}">Delete</button></td>
                </tr>
            `);
          });
        });
      },
      error:function (){
        alert("Failed to fetch orders..!");
      }
    });
  }

  // Delete an order
  $(document).on("click", ".delete-order", function () {
    let orderId = $(this).data("id");

    $.ajax({
      url: `http://localhost:8080/api/v1/order/${orderId}`,
      method: "DELETE",
      success: function () {
        alert("Order deleted successfully.");
        fetchOrders();
      },
      error: function () {
        alert("Failed to delete order.");
      }
    });
  });

  // Initialize
  generateOrderDetails();
  loadCustomers();
  loadItems();
  fetchOrders();

});
