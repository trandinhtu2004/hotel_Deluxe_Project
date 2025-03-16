/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

document.addEventListener("DOMContentLoaded", function () {
    let table = document.querySelector("#bookingTable");
    new simpleDatatables.DataTable(table);
});

function confirmAction(form) {
    let action = form.querySelector("button[name='submit']").value;
    let message = "";

    if (action === "accept") {
        message = "Are you sure you want to accept this reservation?";
    } else if (action === "cancel") {
        message = "Are you sure you want to cancel this reservation?";
    } else if (action === "late") {
        message = "Are you sure you want to mark this order as 'Late'?";
    }

    return confirm(message);
}

document.addEventListener("DOMContentLoaded", function () {
    var detailButtons = document.querySelectorAll('.btn-detail');
    detailButtons.forEach(function (btn) {
        btn.addEventListener('click', function () {
            var roomNumber = btn.getAttribute('data-roomnumber');
            var roomType = btn.getAttribute('data-roomtype');
            var bookingDate = btn.getAttribute('data-bookingdate');
            var checkInDate = btn.getAttribute('data-checkindate');
            var checkOutDate = btn.getAttribute('data-checkoutdate');
            var totalPrice = btn.getAttribute('data-totalprice');
            var bookingStatus = btn.getAttribute('data-bookingstatus');
            var note = btn.getAttribute('data-note');
            var accountFullName = btn.getAttribute('data-accountfullname');

            document.getElementById('roomNumber').textContent = roomNumber;
            document.getElementById('roomType').textContent = roomType;
            document.getElementById('bookingDate').textContent = bookingDate;
            document.getElementById('checkInDate').textContent = checkInDate;
            document.getElementById('checkOutDate').textContent = checkOutDate;
            document.getElementById('totalPrice').textContent = totalPrice;
            document.getElementById('bookingStatus').textContent = bookingStatus;
            document.getElementById('note').textContent = note;
            document.getElementById('fullName').textContent = accountFullName;
        });
    });
});