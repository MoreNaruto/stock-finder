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
            <tr v-for="company in companies" v-bind:key="company">
                <td v-for="key in Object.keys(company)" v-bind:key="key">
                    {{company[key]}}
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
    import api from '../Api';

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
                loading: true,
                error: null
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
                        this.$log.debug("Data loaded: ", response.data);
                        this.companies = response.data
                    })
                    .catch(error => {
                        this.$log.debug(error);
                        this.error = "Failed to load companies";
                    })
                    .finally(() => this.loading = false)
            }
        }
    };

    export default StockList
</script>

<style scoped>

</style>