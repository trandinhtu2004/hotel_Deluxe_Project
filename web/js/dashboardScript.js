/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


$(document).ready(function () {
    
    //Accounts Chart
    var canvas = document.getElementById('accountChart');
    var data1 = parseInt(canvas.getAttribute('data-totalCustomer'), 10);
    var data2 = parseInt(canvas.getAttribute('data-totalStaff'), 10);
    var data3 = parseInt(canvas.getAttribute('data-totalOwner'), 10);
    
    new Chart(document.getElementById('accountChart').getContext('2d'), {
        type: 'radar',
        data: {
            labels: ['Customer', 'Staff', 'Owner'],
            datasets: [{
                    data: [data1, data2, data3],
                    backgroundColor: ['rgba(255, 99, 132, 0.6)', 'rgba(54, 162, 235, 0.6)', 'rgba(255, 206, 86, 0.6)'],
                    borderColor: ['rgba(255,99,132,1)', 'rgba(54, 162, 235, 1)', 'rgba(255, 206, 86, 1)'],
                    borderWidth: 3
                }]
        },
        options: {
            responsive: false,
            plugins: {
                legend: {
                    position: 'left',
                    labels: {
                        boxWidth: 35,
                        usePointStyle: true,
                        padding: 20
                    }
                },
                title: {
                    display: true,
                    text: 'Accounts Chart'
                },
                datalabels: {
                    color: '#000',
                    font: {
                        weight: 'bold',
                        size: 14
                    },
                    anchor: 'center',
                    align: 'center',
                    formatter: function (value, context) {
                        var dataArr = context.chart.data.datasets[0].data;
                        var total = dataArr.reduce(function (sum, val) {
                            return sum + val;
                        }, 0);
                        var percentage = (value / total * 100).toFixed(1);
                        return percentage + '%';
                    }
                }
            }
        },
        plugins: [ChartDataLabels]
    });

    //Rooms Chart
    new Chart(document.getElementById('roomChart').getContext('2d'), {
        type: 'doughnut',
        data: {
            labels: ['Customer', 'Staff', 'Owner'],
            datasets: [{
                    data: [10, 30, 60],
                    backgroundColor: ['rgba(255, 99, 132, 0.6)', 'rgba(54, 162, 235, 0.6)', 'rgba(255, 206, 86, 0.6)'],
                    borderColor: ['rgba(255,99,132,1)', 'rgba(54, 162, 235, 1)', 'rgba(255, 206, 86, 1)'],
                    borderWidth: 3
                }]
        },
        options: {
            responsive: false,
            plugins: {
                legend: {
                    position: 'left',
                    labels: {
                        boxWidth: 35,
                        usePointStyle: true,
                        padding: 20
                    }
                },
                title: {
                    display: true,
                    text: 'Accounts Chart'
                },
                datalabels: {
                    color: '#000',
                    font: {
                        weight: 'bold',
                        size: 14
                    },
                    anchor: 'center',
                    align: 'center',
                    formatter: function (value, context) {
                        var dataArr = context.chart.data.datasets[0].data;
                        var total = dataArr.reduce(function (sum, val) {
                            return sum + val;
                        }, 0);
                        var percentage = (value / total * 100).toFixed(1);
                        return percentage + '%';
                    }
                }
            }
        },
        plugins: [ChartDataLabels]
    });
});
