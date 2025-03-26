/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

$(document).ready(function () {
    var table = $('#bookingTable').DataTable({
        initComplete: function () {
            this.api().columns().every(function () {
                var column = this;
                var colIdx = column.index();

                // Bỏ qua các cột: No (0), Detail (10), Action (11)
                if ([0, 3, 4, 5, 6, 7, 9, 10, 11].includes(colIdx))
                    return;

                // Tạo dropdown
                var select = $('<select class="form-control"><option value="">All</option></select>')
                        .appendTo($(column.footer()))
                        .on('change', function () {
                            var val = $(this).val();
                            column.search(val, {exact: true}).draw();

                        });

                // Xử lý các cột thông thường
                column.data().unique().sort().each(function (d, j) {
                    select.append($('<option>').val(d).text(d));
                });
            });
        },
        columnDefs: [
            {
                targets: [0, 3, 4, 5, 6, 7, 9, 10, 11], // Các cột không cần tìm kiếm
                searchable: false,
                orderable: false
            }
        ]
    });
});

function confirmAction(form, event) {
    event.preventDefault();
    const submitter = event.submitter;
    const action = submitter.value;
    let message = "";
    if (action === "accept") {
        message = "Are you sure you want to accept this reservation?";
    } else if (action === "cancel") {
        message = "Are you sure you want to cancel this reservation?";
    }
    if (confirm(message)) {
        form.submit();
    }
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
            var service = btn.getAttribute('data-service');
            var servicePrice = btn.getAttribute('data-servicePrice');
            var serviceQuantity = btn.getAttribute('data-serviceQuantity');

            document.getElementById('roomNumber').textContent = roomNumber;
            document.getElementById('roomType').textContent = roomType;
            document.getElementById('bookingDate').textContent = bookingDate;
            document.getElementById('checkInDate').textContent = checkInDate;
            document.getElementById('checkOutDate').textContent = checkOutDate;
            document.getElementById('totalPrice').textContent = totalPrice;
            document.getElementById('bookingStatus').textContent = bookingStatus;
            document.getElementById('note').textContent = note;
            document.getElementById('fullName').textContent = accountFullName;
            document.getElementById('service').textContent = service;
            document.getElementById('servicePrice').textContent = servicePrice;
            document.getElementById('serviceQuantity').textContent = serviceQuantity;
        });
    });
});