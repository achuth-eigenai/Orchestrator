locals {
	container_name = "hello-world-container"
	container_port = 8080 # ! Must be same port from our Dockerfile that we EXPOSE
	example = "example-ecs-terraform-aws-modules"
}

