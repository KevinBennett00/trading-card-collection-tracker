//Setup sqlite to manage db connection/query
const sqlite3 = require('sqlite3').verbose();
const hasValidCredentials = require('./account_validator');

//Define SQL queries for user accounts
const createAccountTableQuery =
	'CREATE TABLE IF NOT EXISTS Account (Username TEXT PRIMARY KEY, Password TEXT NOT NULL)';
const insertAccountQuery =
	'INSERT INTO Account (Username, Password) VALUES (?, ?)';
const findAccountQuery =
	'SELECT * FROM Account WHERE Username = ? AND Password = ?';

//Define function to connect to DB
const connectToDB = function() {
	return new sqlite3.Database('./account-store.db',
								sqlite3.OPEN_READWRITE | sqlite3.OPEN_CREATE,
								(err) => {
									if (err) {
										console.error('Connection error:', err.message);
										process.exit(1);
									}
									console.log('Connected to the SQLite database.');
								}
	);
};

//Define function to close DB
const closeDB = function(db) {
	db.close((err) => {
		if (err) {
			return console.error('Closure error:', err.message);
		}
		console.log('Database connection closed.');
	});
};

//Define function to create the account table
const createAccountTable = async (db, sql) => {
	return new Promise((resolve, reject) => {
		db.run(sql, function(err) {
			if (err) {
				reject(err);
			} else {
				resolve();
			}
		});
	});
};

//Define function to insert a new account
const createAccount = async (db, sql, params) => {
	return new Promise((resolve, reject) => {
		db.run(sql, params, function(err) {
			if (err) {
				reject(err);
			} else {
				console.log('Created account for:', params[0]);
				resolve();
			}
		});
	});
};

//Define function to find an account
const findAccount = async (db, sql, params) => {
	return new Promise((resolve, reject) => {
		db.get(sql, params, (err, row) => {
			if (err) {
				reject(err);
			} else {
				resolve(row);
			}
		});
	});
};

//Setup express to handle routing and rendering
var express = require('express');
var app = express();
app.set('view engine', 'ejs');

//Setup bodyparser to extract data from form posts
var bodyParser = require('body-parser');
app.use(bodyParser.urlencoded({ extended: true }));

//Setup static public directory
app.use(express.static('public'));

//Route to login page
app.get('/', function(req, res) {
	res.render('page/login', {error: null});
});

app.get('/login', function(req, res) {
	res.render('page/login', {error: null});
});

//Route to account creation page
app.get('/create-account', function(req, res) {
	res.render('page/create-account', {error: null});
});

//Route to landing page
app.get('/landing', function(req, res) {
	var username = req.query.username;

	if (!username) {
		res.redirect('/login');
		return;
	}

	res.render('page/landing', {username: username});
});

//Route to authenticate an account
app.post('/api/login', async function(req, res) {
	var username = req.body.username;
	var password = req.body.password;

	if (!hasValidCredentials(username, password)) {
		res.render('page/login', {
			error: 'Enter both a username and password.'
		});
		return;
	}

	const db = connectToDB();

	try {
		const account = await findAccount(
			db,
			findAccountQuery,
			[username.trim(), password]
		);

		if (account) {
			res.redirect('/landing?username='
				+ encodeURIComponent(account.Username));
		} else {
			res.render('page/login', {
				error: 'The username or password is incorrect.'
			});
		}
	} catch (err) {
		console.log(err);
		res.render('page/error');
	} finally {
		closeDB(db);
	}
});

//Route to create a new account
app.post('/api/create-account', async function(req, res) {
	var username = req.body.username;
	var password = req.body.password;

	if (!hasValidCredentials(username, password)) {
		res.render('page/create-account', {
			error: 'Enter both a username and password.'
		});
		return;
	}

	username = username.trim();
	const db = connectToDB();

	try {
		await createAccount(
			db,
			insertAccountQuery,
			[username, password]
		);

		res.redirect('/landing?username='
			+ encodeURIComponent(username));
	} catch (err) {
		if (err.code === 'SQLITE_CONSTRAINT') {
			res.render('page/create-account', {
				error: 'That username is already taken.'
			});
		} else {
			console.log(err);
			res.render('page/error');
		}
	} finally {
		closeDB(db);
	}
});

//Create the database table and start the web app
const db = connectToDB();

createAccountTable(db, createAccountTableQuery)
	.then(() => {
		closeDB(db);

		app.listen(8080, function() {
			console.log('App listening on port 8080!');
		});
	})
	.catch((err) => {
		console.log(err);
		closeDB(db);
	});
