'use strict';
const axios = require('axios');
const cheerio = require('cheerio');
const url = 'https://www.detik.com/tag/maritim';

class DetikController {
  async getImageUrl(articleUrl) {
    try {
      const result = await axios.get(articleUrl);
      const $ = cheerio.load(result.data);
      const imgElement = $('div.detail__media').find('img.p_img_zoomin.img-zoomin');
      return imgElement.attr('src');
    } catch (error) {
      console.error(`Failed to fetch image from ${articleUrl}: ${error}`);
      return null;
    }
  }

  async getData({ request, response }) {
    try {
      const result = await axios.get(url);
      const $ = cheerio.load(result.data);

      const articles = [];
      for (let element of Array.from($('article').get())) {
        const article = {};
        const linkElement = $(element).find('a');
        article.newsLink = linkElement.attr('href');
        article.title = $(element).find('h2.title').text().trim();
        article.date = $(element).find('span.date').text().trim();
        article.content = $(element).find('p').text().trim();

        // Get the image URL from the article page
        article.imageUrl = await this.getImageUrl(article.newsLink);

        articles.push(article);
      }

      return response.status(200).json({ data: articles });
    } catch (error) {
      return response.status(500).json({ error: 'Failed to fetch data from Detik' });
    }
  }
}

module.exports = DetikController;
