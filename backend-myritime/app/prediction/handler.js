const axios = require('axios')

module.exports = {
    handlerGetAllFishPrice: async (req,res) => {
        try {
            const result = await axios.get('https://fish-price-prediction-o322inq5pa-et.a.run.app/fishprice');
            res.status(200).json({
                status: 'success',
                data: result.data.prediction,
            });
        } catch (error) {
            res.status(500).json({
                status: 'error',
                message: error.message,
            });
        }
    },
}