function discogsFunction(discogsId) {
	if (discogsId.startsWith("r")) {
		discogsId = discogsId.substr(1);

	}

	fetch("https://api.discogs.com/releases/" + discogsId)
		.then(response => response.json())
		.then(responseData => window.open(responseData.uri, "_blank"))
		.catch((error) => window.alert("Rikkinäinen linkki :("))

}

function deleteRecord(id) {
	if (confirm("Haluatko varmasti poistaa levyn?")) {
		fetch("./delete/" + id)
			.then(() => location.reload());
	}
}


function addToCart(id) {
	if (confirm("Lisätäänkö levy ostoskoriin?")) {
		fetch("./addtocart/" + id)
			.then(response => {
				if (response.ok) {
					alert("Tuote lisätty ostoskoriin!")
					fetch("./records")
				} else {
					alert("Tuote on jo ostoskorissa!");
				}
			})
	}
}


function shoppingCartFunction(rec) {
	console.log(rec);
}


$(document).ready(function() {
	// Setup - add a text input to each footer cell
	$('#recordTable tfoot th').each(function() {
		var title = $(this).text();
		$(this).html('<input type="text" placeholder="Etsi ' + title + '" />');
	});

	// DataTable
	var table = $('#recordTable').DataTable({
		initComplete: function() {
			this.api()
				.columns()
				.every(function() {
					var that = this;

					$('input', this.footer()).on('keyup change clear', function() {
						if (that.search() !== this.value) {
							that.search(this.value).draw();
						}
					});
				});
			$('#recordTable tfoot tr').appendTo('#recordTable thead');
		},
		"bInfo": false,
		"bLengthChange": false,
		"pageLength": 25
	});

	$('.dataTables_length').addClass('bs-select');
});
