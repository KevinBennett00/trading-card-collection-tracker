const hasValidCredentials = function(username, password) {
	if (typeof username !== 'string' || !username.trim()) {
		return false;
	}

	if (typeof password !== 'string' || !password.trim()) {
		return false;
	}

	return true;
};

module.exports = hasValidCredentials;
