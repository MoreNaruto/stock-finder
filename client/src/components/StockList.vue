<template>
    <div>
        <label>
            <input v-model="companyFieldText" placeholder="Enter a company name" v-on:keyup.enter="retrieveCompanies">
        </label>
        <table>
            <thead>
            <tr>
                <th v-for="keys in keys" v-bind:key="keys">
                    {{ keys }}
                </th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="company in companies" v-bind:key="company" @click="createChart(company['symbol'])">
                <td v-for="key in Object.keys(company)" v-bind:key="key">
                    {{company[key]}}
                </td>
            </tr>
            </tbody>
        </table>
        <canvas id="price-history-chart"></canvas>
    </div>
</template>

<script>
    import api from '../Api';
    import Chart from 'chart.js';

    const months = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];
    const StockList = {
        name: 'StockList',
        data: function () {
            return {
                companyFieldText: "",
                companies: [],
                keys: [
                    "Symbol",
                    "Name",
                    "Last Sale",
                    "Market Cap",
                    "IPO Year",
                    "Sector",
                    "Industry",
                    "Summary Quote"
                ],
            }
        },

        methods: {
            retrieveCompanies: function () {
                const value = this.companyFieldText && this.companyFieldText.trim();

                if (!value) {
                    return
                }

                api.retrieveCompaniesByPrefixName(value)
                    .then(response => {
                        this.companies = response.data
                    })
            },
            createChart: function (symbol) {
                api.retrievePriceHistory(symbol)
                    .then(response => {
                        const ctx = document.getElementById('price-history-chart');
                        const priceHistory = response.data;

                        const labels = priceHistory.map(priceDay => {
                            const priceDate = new Date(priceDay['date']);
                            return months[priceDate.getUTCMonth()] + " " + priceDate.getUTCDate();
                        });

                        const averages = priceHistory.map(priceDay => {
                            return priceDay['average']
                        });

                        new Chart(ctx, {
                            type: 'line',
                            data: {
                                labels: labels,
                                datasets: [
                                    {
                                        label: symbol + " price history",
                                        data: averages
                                    }
                                ]
                            }
                        });
                    });
            }
        }
    };

    export default StockList
</script>

<style scoped>

</style>