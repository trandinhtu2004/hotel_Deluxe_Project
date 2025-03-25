/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

document.addEventListener("DOMContentLoaded", function () {
    let table = document.querySelector("#bookingTable");
    new simpleDatatables.DataTable(table);
});

document.addEventListener("DOMContentLoaded", function() {
    // Khởi tạo DataTable trên bảng có id="bookingTable"
    const dataTable = new simpleDatatables.DataTable("#bookingTable");

    // Lưu dữ liệu gốc (mảng các hàng) để có thể khôi phục lại khi filter 'all'
    const originalData = dataTable.data;

    // Hàm filter, được gắn vào global để gọi từ HTML
    window.filterBooking = function(status, btn) {
        // Cập nhật active state cho các nút filter
        document.querySelectorAll('.status-filter .btn').forEach(function(button) {
            button.classList.remove('active');
        });
        btn.classList.add('active');

        // Nếu chọn 'all' thì khôi phục dữ liệu gốc
        if (status === 'all') {
            dataTable.rows().update(originalData);
        } else {
            // Lọc dữ liệu gốc: giả sử trạng thái booking nằm ở cột thứ 9 (index 8)
            const filteredData = originalData.filter(function(row) {
                return row[8].toString().trim() === status;
            });
            // Cập nhật bảng với dữ liệu đã lọc
            dataTable.rows().update(filteredData);
        }
    }
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