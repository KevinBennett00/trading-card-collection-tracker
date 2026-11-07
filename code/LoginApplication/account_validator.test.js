const hasValidCredentials = require('./account_validator');

test('accepts a valid username and password', function() {
	expect(hasValidCredentials('kevin', 'bennett')).toBe(true);
});

test('rejects a blank username', function() {
	expect(hasValidCredentials('', 'bennett')).toBe(false);
});

test('rejects a username containing only spaces', function() {
	expect(hasValidCredentials('   ', 'bennett')).toBe(false);
});

test('rejects a blank password', function() {
	expect(hasValidCredentials('kevin', '')).toBe(false);
});

test('rejects a password containing only spaces', function() {
	expect(hasValidCredentials('kevin', '   ')).toBe(false);
});

test('rejects missing credentials', function() {
	expect(hasValidCredentials(undefined, undefined)).toBe(false);
});
