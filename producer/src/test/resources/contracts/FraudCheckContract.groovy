package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method 'PUT'
        url '/fraudcheck'
        body([
                "clientId": $(regex('[0-9]{10}')),
                "loanAmount": 99999
        ])
        headers {
            contentType('application/json')
        }
    }
    response {
        status OK()
        body([
                "fraudCheckStatus": "FRAUD",
                "rejectionReason" : "Amount too high"
        ])
        headers {
            contentType('application/json')
        }
    }
}