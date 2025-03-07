/*
let cart = [];
$(document).ready(function (){
  let orderList = [];

  //Generate order id and date
  function generateOrderDetails(){
    let nextId = 1;
    if (orderList.length > 0) {
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
  $("#order-form").submit(function (event) {
    event.preventDefault();

    let orderId = $("#order-id").text();
    let customerId = $("#customer-select").val();
    let orderDate = $("#order-date").text();
    let itemId = $("#item-select").val();
    let qty = $("#quantity").val();
    let totalPrice = $("#totalPrice").val();

    if (!customerId || !itemId || !qty) {
      alert("Please fill in all fields..!");
      return;
    }

    let orderData = {
      id: orderId,
      date: orderDate,
      customer: {id: parseInt(customerId)},
      orderDetails: [
        {
          item: {id: parseInt(itemId)},
          qty: parseInt(qty),
          totalPrice: parseFloat(totalPrice)
        }
      ]
    };

    $.ajax({
      url: "http://localhost:8080/api/v1/order/place",
      method: "POST",
      contentType: "application/json",
      data: JSON.stringify(orderData),
      success: function (response) {
        alert("Order placed successfully..!");
        fetchOrders();
        generateOrderDetails();
      },
      error: function () {
        alert("Failed to place order..");
      }
    });
  });
  /!*function fetchOrders() {
    $.ajax({
      url: "http://localhost:8080/api/v1/order",
      method: "GET",
      success: function (orders) {
        let tableBody = $("#order-table-body");
        tableBody.empty();

        orders.forEach(order => {
          order.orderDetails.forEach(detail => {
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
      error: function () {
        alert("Failed to fetch orders..!");
      }
    });
  }*!/

  function fetchOrders() {
    $.ajax({
      url: "http://localhost:8080/api/v1/order/getAll",
      method: "GET",
      success: function (orders) {
        console.log("Orders fetched:",orders);
        let tableBody = $("#order-table-body");
        tableBody.empty();

        orders.forEach(order => {
          order.orderDetails.forEach(detail => {
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
      error: function () {
        alert("Failed to fetch orders.");
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

  $('#addToCart').on('click', function () {
    const itemId = $('#item-select').val();
    const itemName = $('#item-select option:selected').text();
    const unitPrice = parseFloat($('#unitPrice').val());
    const quantity = parseInt($('#quantity').val());
    const totalPrice = unitPrice * quantity;

    if (!itemId || !quantity || quantity <= 0) {
      alert("Please select an item and enter a valid quantity.");
      return;
    }

    const existingItem = cart.find(item => item.itemId === itemId);
    if (existingItem) {
      existingItem.quantity += quantity;
      existingItem.totalPrice += totalPrice;
    } else {
      cart.push({itemId, itemName, unitPrice, quantity, totalPrice});
    }

    updateCartTable();
    clearItemFields();
  });

  // Function to update cart table
  function updateCartTable() {
    const tableBody = $('#cart-table-body');
    tableBody.empty();

    cart.forEach((item, index) => {
      const row = `
                <tr>
                    <td>${item.itemId}</td>
                    <td>${item.itemName}</td>
                    <td>${item.unitPrice.toFixed(2)}</td>
                    <td>${item.quantity}</td>
                    <td>${item.totalPrice.toFixed(2)}</td>
                    <td><button class="btn btn-danger btn-sm" onclick="removeFromCart(${index})">Remove</button></td>
                </tr>
            `;
      tableBody.append(row);
    });
  }

  // Function to clear input fields after adding to cart
  function clearItemFields() {
    $('#item-select').val('');
    $('#unitPrice').val('');
    $('#quantity').val('');
    $('#totalPrice').val('');
  }

  // Expose remove function to global scope
  window.removeFromCart = function (index) {
    cart.splice(index, 1);
    updateCartTable();
  };

  // Calculate total price when quantity changes
  $('#quantity').on('input', function () {
    const unitPrice = parseFloat($('#unitPrice').val()) || 0;
    const quantity = parseInt($(this).val()) || 0;
    $('#totalPrice').val((unitPrice * quantity).toFixed(2));
  });
  // Initialize
  generateOrderDetails();
  loadCustomers();
  loadItems();
  fetchOrders();

});
*/

/*const BASE_URL = 'http://localhost:8080/api/v1';

// Global cart to hold items temporarily before placing order
let cart = [];

document.addEventListener('DOMContentLoaded', function () {
  loadCustomers();
  loadItems();
  generateOrderId();
  setOrderDate();

  document.getElementById('item-select').addEventListener('change', loadItemDetails);
  document.getElementById('addToCart').addEventListener('click', addItemToCart);
  document.getElementById('placeOrder').addEventListener('click', placeOrder);

  document.getElementById('quantity').addEventListener('input', calculateTotalPrice);
});

function loadCustomers() {
  fetch(`${BASE_URL}/customer/getAll`)
    .then(response => response.json())
    .then(customers => {
      const customerSelect = document.getElementById('customer-select');
      customerSelect.innerHTML = '<option value="">Select Customer</option>';
      customers.forEach(customer => {
        const option = document.createElement('option');
        option.value = customer.id;
        option.textContent = `${customer.id} - ${customer.name}`;
        customerSelect.appendChild(option);
      });
    })
    .catch(error => console.error('Error loading customers:', error));
}

function loadItems() {
  fetch(`${BASE_URL}/item/getAll`)
    .then(response => response.json())
    .then(items => {
      const itemSelect = document.getElementById('item-select');
      itemSelect.innerHTML = '<option value="">Select Item</option>';
      items.forEach(item => {
        const option = document.createElement('option');
        option.value = item.id;
        option.textContent = `${item.id} - ${item.name}`;
        option.dataset.price = item.price;
        option.dataset.qtyOnHand = item.qtyOnHand;
        itemSelect.appendChild(option);
      });
    })
    .catch(error => console.error('Error loading items:', error));
}

function loadItemDetails() {
  const itemSelect = document.getElementById('item-select');
  const selectedOption = itemSelect.options[itemSelect.selectedIndex];

  if (!selectedOption.value) {
    clearItemFields();
    return;
  }

  const unitPrice = selectedOption.dataset.price;
  const qtyOnHand = selectedOption.dataset.qtyOnHand;

  document.getElementById('unitPrice').value = unitPrice || '';
  document.getElementById('qtyOnHand').value = qtyOnHand || '';
  calculateTotalPrice();
}

function calculateTotalPrice() {
  const unitPrice = parseFloat(document.getElementById('unitPrice').value) || 0;
  const quantity = parseInt(document.getElementById('quantity').value) || 0;

  if (unitPrice > 0 && quantity > 0) {
    document.getElementById('totalPrice').value = (unitPrice * quantity).toFixed(2);
  } else {
    document.getElementById('totalPrice').value = '';
  }
}

function addItemToCart() {
  const itemSelect = document.getElementById('item-select');
  const itemId = itemSelect.value;
  const itemName = itemSelect.options[itemSelect.selectedIndex].text;
  const unitPrice = parseFloat(document.getElementById('unitPrice').value) || 0;
  const quantity = parseInt(document.getElementById('quantity').value) || 0;
  const totalPrice = unitPrice * quantity;

  if (!itemId || quantity <= 0) {
    alert("Please select a valid item and enter a valid quantity.");
    return;
  }

  const existingItem = cart.find(item => item.itemId === itemId);
  if (existingItem) {
    existingItem.quantity += quantity;
    existingItem.totalPrice += totalPrice;
  } else {
    cart.push({ itemId, itemName, unitPrice, quantity, totalPrice });
  }

  updateCartTable();
  clearItemFields();
}

function updateCartTable() {
  const tableBody = document.getElementById('cart-table-body');
  tableBody.innerHTML = '';

  cart.forEach((item, index) => {
    const row = `
            <tr>
                <td>${item.itemId}</td>
                <td>${item.itemName}</td>
                <td>${item.unitPrice.toFixed(2)}</td>
                <td>${item.quantity}</td>
                <td>${item.totalPrice.toFixed(2)}</td>
                <td><button class="btn btn-danger btn-sm" onclick="removeFromCart(${index})">Remove</button></td>
            </tr>
        `;
    tableBody.innerHTML += row;
  });

  updateCartTotal();
}

function updateCartTotal() {
  const total = cart.reduce((sum, item) => sum + item.totalPrice, 0);
  document.getElementById('grandTotal').innerText = total.toFixed(2);
}

function clearItemFields() {
  document.getElementById('item-select').value = '';
  document.getElementById('unitPrice').value = '';
  document.getElementById('qtyOnHand').value = '';
  document.getElementById('quantity').value = '';
  document.getElementById('totalPrice').value = '';
}

function removeFromCart(index) {
  cart.splice(index, 1);
  updateCartTable();
}

function placeOrder() {
  const customerId = document.getElementById('customer-select').value;
  if (!customerId) {
    alert("Please select a customer.");
    return;
  }

  if (cart.length === 0) {
    alert("Please add at least one item to the order.");
    return;
  }

  const orderDetails = cart.map(item => ({
    item: { id: parseInt(item.itemId) },
    qty: item.quantity,
    totalPrice: item.totalPrice
  }));

  const orderData = {
    customer: { id: parseInt(customerId) },
    orderDetails: orderDetails
  };

  fetch(`${BASE_URL}/order/place`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(orderData)
  })
    .then(response => {
      if (response.ok) {
        alert("Order placed successfully!");
        cart = [];
        updateCartTable();
        clearOrderForm();
      } else {
        alert("Failed to place order.");
      }
    })
    .catch(error => {
      console.error('Error placing order:', error);
      alert("Error placing order.");
    });
}

function clearOrderForm() {
  cart = [];
  updateCartTable();
  document.getElementById('customer-select').value = '';
  document.getElementById('order-id').innerText = generateOrderId();
  document.getElementById('order-date').innerText = setOrderDate();
  clearItemFields();
}

function generateOrderId() {
  const orderId = 'ORD' + Math.floor(Math.random() * 10000);
  document.getElementById('order-id').innerText = orderId;
}

function setOrderDate() {
  const today = new Date().toISOString().split('T')[0];
  document.getElementById('order-date').innerText = today;
}*/

//=============================================================================
let items = [];
let cart = [];
let grandTotal = 0;

// Load initial data when the page loads
$(document).ready(function () {
  loadCustomers();
  loadItems();
  generateOrderId();
  $('#order-date').text(new Date().toISOString().split('T')[0]);

  $('#item-select').on('change', handleItemSelect);
  $('#addToCart').on('click', addToCart);
  $('#placeOrder').on('click', placeOrder);
  $('#clear').on('click', clearForm);
});

// Load customers from backend
function loadCustomers() {
  fetch('http://localhost:8080/api/v1/customer/getAll')
    .then(response => response.json())
    .then(data => {
      data.forEach(customer => {
        $('#customer-select').append(`<option value="${customer.id}">${customer.name}</option>`);
      });
    })
    .catch(error => console.error('Error loading customers:', error));
}

// Load items from backend
function loadItems() {
  fetch('http://localhost:8080/api/v1/item/getAll')
    .then(response => response.json())
    .then(data => {
      items = data;
      data.forEach(item => {
        $('#item-select').append(`<option value="${item.id}">${item.name}</option>`);
      });
    })
    .catch(error => console.error('Error loading items:', error));
}

// Generate new order ID (You could also fetch from backend if required)
function generateOrderId() {
  fetch('http://localhost:8080/api/v1/order/nextId')
    .then(response => response.text())
    .then(orderId => {
      $('#order-id').text(orderId);
    })
    .catch(error => console.error('Error generating order ID:', error));
}

// Handle item selection
function handleItemSelect() {
  let selectedItemId = $(this).val();
  let selectedItem = items.find(item => item.id == selectedItemId);

  if (selectedItem) {
    $('#unitPrice').val(selectedItem.price.toFixed(2));
    $('#qtyOnHand').val(selectedItem.qtyOnHand);
  } else {
    $('#unitPrice').val('');
    $('#qtyOnHand').val('');
  }
}

// Add selected item to cart
function addToCart() {
  let itemId = $('#item-select').val();
  let quantity = parseInt($('#quantity').val());

  if (!itemId || isNaN(quantity) || quantity <= 0) {
    alert('Please select an item and enter valid quantity.');
    return;
  }

  let selectedItem = items.find(item => item.id == itemId);

  if (!selectedItem) {
    alert('Invalid item selected.');
    return;
  }

  if (quantity > selectedItem.qtyOnHand) {
    alert('Not enough stock available.');
    return;
  }

  let existingCartItem = cart.find(item => item.itemId == itemId);

  if (existingCartItem) {
    existingCartItem.quantity += quantity;
    existingCartItem.totalPrice += quantity * selectedItem.price;
  } else {
    cart.push({
      itemId: itemId,
      itemName: selectedItem.name,
      unitPrice: selectedItem.price,
      quantity: quantity,
      totalPrice: quantity * selectedItem.price
    });
  }

  updateCartTable();
  calculateGrandTotal();
}

// Update cart table
function updateCartTable() {
  let cartTableBody = $('#cart-table-body');
  cartTableBody.empty();

  cart.forEach((item, index) => {
    cartTableBody.append(`
            <tr>
                <td>${item.itemId}</td>
                <td>${item.itemName}</td>
                <td>${item.unitPrice.toFixed(2)}</td>
                <td>${item.quantity}</td>
                <td>${item.totalPrice.toFixed(2)}</td>
                <td><button class="btn btn-danger btn-sm" onclick="removeCartItem(${index})">Remove</button></td>
            </tr>
        `);
  });
}

// Calculate grand total
function calculateGrandTotal() {
  grandTotal = cart.reduce((sum, item) => sum + item.totalPrice, 0);
  $('#grandTotal').text(grandTotal.toFixed(2));
}

// Remove item from cart
function removeCartItem(index) {
  cart.splice(index, 1);
  updateCartTable();
  calculateGrandTotal();
}

// Place order
function placeOrder(event) {
  event.preventDefault();

  let customerId = $('#customer-select').val();
  let orderDate = $('#order-date').text();

  if (!customerId || cart.length === 0) {
    alert('Please select customer and add items to cart.');
    return;
  }

  let orderDTO = {
    customerId: customerId,
    date: orderDate,
    orderDetails: cart.map(item => ({
      itemId: item.itemId,
      quantity: item.quantity,
      unitPrice: item.unitPrice
    }))
  };

  fetch('http://localhost:8080/api/v1/order/place', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(orderDTO)
  })
    .then(response => {
      if (response.ok) {
        alert('Order placed successfully!');
        clearForm();
        loadOrderDetails();
      } else {
        response.text().then(text => alert('Failed to place order: ' + text));
      }
    })
    .catch(error => console.error('Error placing order:', error));
}

// Clear form
function clearForm() {
  cart = [];
  updateCartTable();
  calculateGrandTotal();
  $('#customer-select').val('');
  $('#item-select').val('');
  $('#unitPrice').val('');
  $('#qtyOnHand').val('');
  $('#quantity').val('');
  $('#totalPrice').val('');
  generateOrderId();
}

// Load all orders (you can load it when page loads or after order is placed)
function loadOrderDetails() {
  fetch('http://localhost:8080/api/v1/order/getAll')
    .then(response => response.json())
    .then(orders => {
      const orderTableBody = $('#order-table-body');
      orderTableBody.empty();

      orders.forEach(order => {
        order.orderDetails.forEach(detail => {
          const row = `
                        <tr>
                            <td>${order.id}</td>
                            <td>${order.customerId}</td>
                            <td>${detail.itemId}</td>
                            <td>${detail.quantity}</td>
                            <td>${(detail.unitPrice * detail.quantity).toFixed(2)}</td>
                            <td><button class="btn btn-info btn-sm" onclick="viewOrder(${order.id})">View Order</button></td>
                        </tr>
                    `;
          orderTableBody.append(row);
        });
      });
    })
    .catch(error => console.error('Error loading order details:', error));
}

// Optional: View order (if you want to load detailed view by orderId)
function viewOrder(orderId) {
  fetch(`http://localhost:8080/api/v1/order/${orderId}`)
    .then(response => response.json())
    .then(order => {
      alert(`Order ID: ${order.id}\nDate: ${order.date}\nCustomer: ${order.customerId}`);
    })
    .catch(error => console.error('Error viewing order:', error));
}




