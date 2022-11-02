function deleteFromShoppingCart(id) {
	if (confirm("Haluatko poistaa levyn ostoskorista?")) {
		fetch("./deletefromshoppingcart/" + id)
			.then(() => location.reload());
	}
}