<template>
    <div>
        {{this.companies}}
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
                loading: true,
                error: null
            }
        },

        mounted() {
            api.retrieveCompaniesByPrefixName("app")
                .then(response => {
                    this.$log.debug("Data loaded: ", response.data);
                    this.companies = response.data
                })
                .catch(error => {
                    this.$log.debug(error);
                    this.error = "Failed to load companies";
                })
                .finally(() => this.loading = false)
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