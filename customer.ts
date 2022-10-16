const baseUrl = "http://localhost:8080/ex_006_draxler-1.0-SNAPSHOT"

const getAllCustomers = () => {
    fetch(`${baseUrl}/api/customers`).then(response => {
        return response.json();
    }).then(data => {
        const customerList = data;
        let html = ""
        customerList.forEach(c => html += `<tr><td>${c.id}</td><td>${c.name}</td><td>${c.address}</td></tr>`);
        document.getElementById("customerTable").innerHTML = html;
    });
};

const onAddCustomer = (id, name, address, sales) => {
    let customerToAdd = {
        id: id, name: name, address: address, sales: sales
    };
    fetch(`${baseUrl}/api/customers`, {
        method: "POST", headers: {"Content-Type": "application/json"}, body: JSON.stringify(customerToAdd)
    }).then(response => {
        alert(response.status + " " + response.headers.get("Location"));
    })

}


const onGetCustomer = (id) => {
    let resultDiv = document.getElementById("divResult");
    resultDiv.innerText = "";
    fetch(`${baseUrl}/api/customers/${id}`)
        .then(response => {
            return response.json();
        })
        .then(data => {
            const customer = data;

            resultDiv.innerText = `${customer.id} ${customer.name} ${customer.sales}`;
        }).catch(error => {
        alert("Invalid Request: Customer with " + id + " was not found" );

    });

};


const onRemoveCustomer = (id) => {

    fetch(`${baseUrl}/api/customers/${id}`, {
        method: "DELETE"
    }).then(response => {
        alert(response.status);
    });
};


