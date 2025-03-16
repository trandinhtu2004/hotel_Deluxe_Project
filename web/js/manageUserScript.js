/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

// Table script
document.addEventListener("DOMContentLoaded", function () {
    // Khởi tạo DataTable
    const dataTable = new simpleDatatables.DataTable(".user-list");

    // Tạo tiêu đề hiển thị nếu chưa có
    let title = document.getElementById("tittle");
    if (!title) {
        title = document.createElement("h2");
        title.id = "tittle";
        title.textContent = "All Users";
        document.querySelector(".main-box-body").insertBefore(title, document.querySelector(".main-box-body").firstChild);
    }

    // Lấy danh sách các mục filter
    const filters = document.querySelectorAll(".nav-contacts li");

    filters.forEach(filter => {
        filter.addEventListener("click", function () {
            // Lấy giá trị filter và chuyển thành chữ thường
            const role = this.getAttribute("data-filter").toLowerCase();

            // Cập nhật tiêu đề hiển thị
            if (role === "all") {
                title.textContent = "All Users";
                dataTable.search('');  // Xóa bộ lọc
            } else {
                title.textContent = "All " + role.charAt(0).toUpperCase() + role.slice(1);
                // Tìm kiếm trên cột ẩn chứa role (index 5)
                dataTable.search(role, [5]);
            }

            // Cập nhật active class cho bộ lọc
            filters.forEach(f => f.classList.remove("active"));
            this.classList.add("active");
        });
    });
});

function updateStatusColors() {
    var statusLabels = document.querySelectorAll('.user-list .label');
    statusLabels.forEach(function (label) {
        var statusText = label.textContent.trim();
        if (statusText === 'Active') {
            label.classList.remove('label-default');
            label.classList.add('label-success');
        } else if (statusText === 'Inactive') {
            label.classList.remove('label-default');
            label.classList.add('label-danger');
        }
    });
}
document.addEventListener('datatable:updated', updateStatusColors);

//Modal Edit User Script
document.addEventListener("DOMContentLoaded", function () {
    var editButtons = document.querySelectorAll('.btn-edit');
    editButtons.forEach(function (btn) {
        btn.addEventListener('click', function () {
            var accountId = btn.getAttribute('data-accountId')
            var fullName = btn.getAttribute('data-fullname');
            var email = btn.getAttribute('data-email');
            var phone = btn.getAttribute('data-phone');
            var address = btn.getAttribute('data-address');
            var roleId = btn.getAttribute('data-role-id');
            var roleName = btn.getAttribute('data-role-name');
            var createdDate = btn.getAttribute('data-createdDate');
            var status = btn.getAttribute('data-status');

            status = (status && status.trim().toLowerCase() === "active") ? "Active" : "Inactive";

            document.getElementById('editAccountId').value = accountId;
            document.getElementById('editFullName').value = fullName;
            document.getElementById('editEmail').value = email;
            document.getElementById('editPhone').value = phone;
            document.getElementById('editAddress').value = address;
            document.getElementById('editRoleId').value = roleId;
            document.getElementById('editRoleName').value = roleName;
            document.getElementById('editCreatedDate').value = createdDate;
            document.getElementById('editStatus').value = status;

            var statusSpan = document.getElementById('editStatusDisplay');
            if (status === "Active") {
                statusSpan.className = "status status-active";
            } else {
                statusSpan.className = "status status-inactive";
            }
            statusSpan.innerHTML = status + " <span class='status-dot'></span>";

            var editBanButton = document.getElementById("editBanButton");
            if (status === "Active") {
                editBanButton.textContent = "Ban";
                editBanButton.className = "btn btn-danger btn-ban-edit";
            } else {
                editBanButton.textContent = "Active";
                editBanButton.className = "btn btn-success btn-ban-edit";
            }
            document.querySelector("#modal-edit-user input[name='action']").value = "edit";
        });
    });

    var editBanButton = document.getElementById("editBanButton");
    editBanButton.addEventListener("click", function () {
        var currentStatus = document.getElementById("editStatus").value;
        var newStatus = (currentStatus === "Active") ? "Inactive" : "Active";
        document.getElementById("editStatus").value = newStatus;

        var statusSpan = document.getElementById('editStatusDisplay');
        if (newStatus === "Active") {
            statusSpan.className = "status status-active";
            editBanButton.textContent = "Ban";
            editBanButton.className = "btn btn-danger btn-ban-edit";
        } else {
            statusSpan.className = "status status-inactive";
            editBanButton.textContent = "Active";
            editBanButton.className = "btn btn-success btn-ban-edit";
        }
        statusSpan.innerHTML = newStatus + " <span class='status-dot'></span>";

        document.querySelector("#modal-edit-user input[name='action']").value = "ban";

    });
});

function validateEditFullName() {
    var fullName = document.getElementById("editFullName").value.trim();

    var fullNameRegex = /^[A-ZÀ-Ỹ][a-zà-ỹ]+(?:\s[A-ZÀ-Ỹ][a-zà-ỹ]+)*$/;

    if (!fullNameRegex.test(fullName)) {
        document.getElementById("editFullName_error").innerText = "Capitalize the first letter of each word please!";
    } else {
        document.getElementById("editFullName_error").innerText = "";
    }
}

function validateEditPhoneNumber() {
    var phoneNumber = document.getElementById("editPhone").value.trim();

    var phoneNumberRegex = /^(0[35789])\d{8}$/;

    if (!phoneNumberRegex.test(phoneNumber)) {
        document.getElementById("editPhone_error").innerText = "Wrong format phone number,\n Example: 0943614388\n Enter again please!";
    } else {
        document.getElementById("editPhone_error").innerText = "";
    }
}

function validateEditAddress() {
    var address = document.getElementById("editAddress").value.trim();

    var addressRegex = /^(?:[A-ZÀ-Ỹ][a-zà-ỹ]*|\d+)(?:\s[A-ZÀ-Ỹ][a-zà-ỹ]*|\s\d+)*$/;

    if (!addressRegex.test(address)) {
        document.getElementById("editAddress_error").innerText = "Capitalize the first letter of each word please!";
    } else {
        document.getElementById("editAddress_error").innerText = "";
    }
}

document.getElementById("editFullName").addEventListener("input", validateEditFullName);
document.getElementById("editPhone").addEventListener("input", validateEditPhoneNumber);
document.getElementById("editAddress").addEventListener("input", validateEditAddress);

//Modal Add User Script
document.addEventListener("DOMContentLoaded", function () {
    var createdDateInput = document.getElementById("createdDate");
    var today = new Date().toISOString().split("T")[0];
    createdDateInput.value = today;
});

function validateFullName() {
    var fullName = document.getElementById("fullName").value.trim();

    var fullNameRegex = /^[A-ZÀ-Ỹ][a-zà-ỹ]+(?:\s[A-ZÀ-Ỹ][a-zà-ỹ]+)*$/;

    if (!fullNameRegex.test(fullName)) {
        document.getElementById("fullName_error").innerText = "Capitalize the first letter of each word please!";
    } else {
        document.getElementById("fullName_error").innerText = "";
    }
}

function validateEmail() {
    var email = document.getElementById("email").value.trim();

    var emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

    if (!emailRegex.test(email)) {
        document.getElementById("email_error").innerText = "Wrong format email,\n Example: example@gmail.com\n Enter again please!";
    } else {
        document.getElementById("email_error").innerText = "";
    }
}

function validatePhoneNumber() {
    var phoneNumber = document.getElementById("phone").value.trim();

    var phoneNumberRegex = /^(0[35789])\d{8}$/;

    if (!phoneNumberRegex.test(phoneNumber)) {
        document.getElementById("phone_error").innerText = "Wrong format phone number,\n Example: 0943614388\n Enter again please!";
    } else {
        document.getElementById("phone_error").innerText = "";
    }
}

function validateAddress() {
    var address = document.getElementById("address").value.trim();

    var addressRegex = /^(?:[A-ZÀ-Ỹ][a-zà-ỹ]*|\d+)(?:\s[A-ZÀ-Ỹ][a-zà-ỹ]*|\s\d+)*$/;

    if (!addressRegex.test(address)) {
        document.getElementById("address_error").innerText = "Capitalize the first letter of each word please!";
    } else {
        document.getElementById("address_error").innerText = "";
    }
}

function validatePassword() {
    var password = document.getElementById("password").value.trim();

    var passwordRegex = /^(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{6,18}$/;

    if (!passwordRegex.test(password)) {
        document.getElementById("password_error").innerText = "Password must be 6-18 characters\nAt least 1 uppercase letter, 1 lowercase letter and 1 special character\nEnter again please!";
    } else {
        document.getElementById("password_error").innerText = "";
    }
}

function validateConfirmPass() {
    var password = document.getElementById("password").value.trim();
    var confirmPass = document.getElementById("confirmPassword").value.trim();

    if (confirmPass !== password) {
        document.getElementById("confirmPassword_error").innerText = "Password and Confirm password are different\n Enter again please!";
    } else {
        document.getElementById("confirmPassword_error").innerText = "";
    }
}

document.getElementById("fullName").addEventListener("input", validateFullName);
document.getElementById("email").addEventListener("input", validateEmail);
document.getElementById("phone").addEventListener("input", validatePhoneNumber);
document.getElementById("address").addEventListener("input", validateAddress);
document.getElementById("password").addEventListener("input", validatePassword);
document.getElementById("confirmPassword").addEventListener("input", validateConfirmPass);