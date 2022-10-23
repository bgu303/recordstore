function testFunction(discogsId) {
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


$(document).ready(function() {
	$('#recordTable').DataTable({
		"bInfo": false,
		"bLengthChange": false
	});
	$('.dataTables_length').addClass('bs-select');
});
