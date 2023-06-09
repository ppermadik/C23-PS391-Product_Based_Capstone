# News Scraper API Documentation

This document provides the necessary details to interact with the News Scraper API, which is used to fetch news data from specific sources. The News Scraper API is built with AdonisJS in API-only mode.

**Base URL:** https://news-scraper-o322inq5pa-et.a.run.app/

**Repository:** https://github.com/KA13000M/news-scraper.git

## Endpoints

### GET /detik

Retrieves news data from the Detik news source.

**URL:** `/detik`

**Method:** `GET`

**Auth required:** No

#### Success Response

**Code:** 200 OK

**Content example:**

```json
{
    "data": [
        {
            "newsLink": "https://news.detik.com/berita/d-6761649/hari-laut-sedunia-cak-imin-ingatkan-pentingnya-kedaulatan-maritim",
            "title": "Hari Laut Sedunia, Cak Imin Ingatkan Pentingnya Kedaulatan Maritim",
            "date": "detikNewsKamis, 08 Jun 2023 13:16 WIB",
            "content": "Menurutnya, Hari Laut Sedunia adalah momentum untuk menghargai berbagai laut yang ada di dunia, termasuk di Indonesia.",
            "imageUrl": "https://akcdn.detik.net.id/community/media/visual/2023/05/16/ketua-umum-partai-kebangkitan-bangsa-pkb-abdul-muhaimin-iskandar-cak-imin.jpeg?w=700&q=90"
        }
    ]
}
```

**Fields description:**

- `newsLink`: A string that holds the URL of the news article.
- `title`: A string that contains the title of the news article.
- `date`: A string representing the published date of the news article.
- `content`: A string that contains a snippet or brief description from the news article.
- `imageUrl`: A string that contains the URL of the image associated with the news article.

#### Error Response

In case of an error, the response will contain an error message.

**Code:** 400 BAD REQUEST, 500 INTERNAL SERVER ERROR, etc.

**Content example:**

```json
{
    "error": "errorMessage"
}
```

## Deployment

The API is deployed and accessible at https://news-scraper-o322inq5pa-et.a.run.app/detik

## Note

Please ensure that you understand the legal and ethical obligations when performing web scraping. This includes not overloading the server, respecting the `robots.txt` file of the websites, and ensuring you have the necessary permissions to scrape and use the data.

## Here's how you can do it:
<!DOCTYPE html>
<html>
<body>

<h2>News</h2>

<div id="newsContainer"></div>

<script>
fetch('https://news-scraper-o322inq5pa-et.a.run.app/detik')
  .then(response => {
    if (!response.ok) {
      throw new Error('Network response was not ok');
    }
    return response.json();
  })
  .then(data => {
    // Here 'data' is the JSON response from your API. 
    // You can use it to populate your frontend.
    var newsContainer = document.getElementById('newsContainer');

    data.data.forEach(newsItem => {
      var newsElement = document.createElement('div');
      newsElement.innerHTML = `
        <h3>${newsItem.title}</h3>
        <p>${newsItem.date}</p>
        <p>${newsItem.content}</p>
        <img src="${newsItem.imageUrl}" alt="${newsItem.title}">
      `;
      newsContainer.appendChild(newsElement);
    });
  })
  .catch(error => {
    // If there's an error, log it
    console.error('There has been a problem with your fetch operation:', error);
  });
</script>

</body>
</html>
