import axios from 'axios'

const SERVER_URL = 'http://localhost:9000';

const instance = axios.create({
    baseURL: SERVER_URL,
    timeout: 1000
});

export default {
    retrieveCompaniesByPrefixName: (prefix) => instance.get('stocks/find/' + prefix, {
        transformResponse: [function (data) {
            return JSON.parse(data);
        }]
    }),

    retrievePriceHistory: (stockSymbol) => instance.get('stocks/price/history/' + stockSymbol, {
        transformResponse: [function (data) {
            return JSON.parse(data);
        }]
    })
}