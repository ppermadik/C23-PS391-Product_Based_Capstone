const axios = require('axios')

module.exports = {
    handlerGetAllNews: async (req,res) => {
        try {
            const result = await axios.get('https://news-scraper-o322inq5pa-et.a.run.app/detik');
            res.status(200).json({
                status: 'success',
                data: result.data.data,
            });
        } catch (error) {
            res.status(500).json({
                status: 'error',
                message: error.message,
            });
        }
    }
}