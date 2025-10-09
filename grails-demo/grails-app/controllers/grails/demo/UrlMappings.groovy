package grails.demo

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(controller: "dateTime", action: "index")
        "/gson"(controller: "dateTime", action: "gson")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
