/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


$(document).ready(function () {
    // Website Views Chart
    new Chart(document.getElementById('websiteViewsChart').getContext('2d'), {
        type: 'bar',
        data: {
            labels: ['M', 'T', 'W', 'T', 'F', 'S', 'S'],
            datasets: [{
                label: 'Views',
                data: [50, 55, 40, 45, 60, 70, 65],
                backgroundColor: 'rgba(60, 186, 84, 0.8)'
            }]
        },
        options: {
            responsive: true,
            plugins: {
                legend: { display: false }
            }
        }
    });

    // Daily Sales Chart
    new Chart(document.getElementById('dailySalesChart').getContext('2d'), {
        type: 'line',
        data: {
            labels: ['J', 'F', 'M', 'A', 'M', 'J', 'J', 'A', 'S', 'O', 'N', 'D'],
            datasets: [{
                label: 'Sales',
                data: [200, 150, 300, 250, 400, 350, 300, 450, 500, 400, 450, 500],
                borderColor: 'rgba(60, 186, 84, 1)',
                backgroundColor: 'rgba(60, 186, 84, 0.1)',
                fill: true
            }]
        },
        options: {
            responsive: true,
            plugins: {
                legend: { display: false }
            }
        }
    });

    // Completed Tasks Chart
    new Chart(document.getElementById('completedTasksChart').getContext('2d'), {
        type: 'line',
        data: {
            labels: ['Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
            datasets: [{
                label: 'Tasks',
                data: [100, 120, 150, 200, 250, 300, 320, 350, 400],
                borderColor: 'rgba(60, 186, 84, 1)',
                backgroundColor: 'rgba(60, 186, 84, 0.1)',
                fill: true
            }]
        },
        options: {
            responsive: true,
            plugins: {
                legend: { display: false }
            }
        }
    });
});
