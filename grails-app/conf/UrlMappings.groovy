class UrlMappings {

	static mappings = {

		"/cars"(controller: "auto"){
			action = [POST: "post"]
		}

		"/cars/$id"(controller: "auto"){
            action = [GET: "get", PUT: "update",DELETE: "delete"]
        }

		"404" (controller:'error', action:'show')
		"500" (controller:'error', action:'show')
		}
	}

