// First, parse the query string
var params = {}, queryString = location.hash.substring(1),
    regex = /([^&=]+)=([^&]*)/g, m;
while (m = regex.exec(queryString)) {
  params[decodeURIComponent(m[1])] = decodeURIComponent(m[2]);
}

// Send parsed tokens back to server
window.open('https://geodes-and-gemstones.herokuapp.com/user/index/token?' + queryString,'_self')
