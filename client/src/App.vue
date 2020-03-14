<template>
    <div id="app">
        <StockList/>
    </div>
</template>

<script>
    import StockList from './components/StockList'
    const app = {
        name: 'app',
        components: {
            StockList
        },
        data: () => {
            return {}
        }
    };

    export default app
</script>
