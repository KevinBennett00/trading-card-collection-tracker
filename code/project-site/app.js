// Setup Express to handle routing and rendering
var express = require('express');
var app = express();

app.set('view engine', 'ejs');
app.use(express.static('public'));

// Setup body-parser
var bodyParser = require('body-parser');
app.use(bodyParser.urlencoded({ extended: true }));

// Landing page
app.get('/', function(req, res) {
    res.render('page/index');
});

// Documentation and quickstart pages
app.get('/documentation', function(req, res) {
    res.render('page/documentation');
});

app.get('/quickstart', function(req, res) {
    res.render('page/quickstart');
});

// Feature pages
app.get('/features/view-collection', function(req, res) {
    res.render('page/features/view-collection');
});

app.get('/features/add-card', function(req, res) {
    res.render('page/features/add-card');
});

app.get('/features/view-details', function(req, res) {
    res.render('page/features/view-details');
});

app.get('/features/edit-card', function(req, res) {
    res.render('page/features/edit-card');
});

app.get('/features/remove-card', function(req, res) {
    res.render('page/features/remove-card');
});

// Start the web app
app.listen(8080, function() {
    console.log('App listening on port 8080!');
});