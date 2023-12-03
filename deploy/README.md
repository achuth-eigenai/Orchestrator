# Pre-requesits

## Profile set up.

create your profile in your ~/.aws/credentials file against each environment.

## Key Pair

for fresh account deployment the ecs-infra requires you to have a keypair created under ec2.

## Deployment

### Non Prod:

```shell
$ ./vpc-infra.sh -s dev -r ap-southeast-1
$ ./ecs-infra.sh -s dev -r ap-southeast-1 -a 828109562156
$ ./release.sh -r ap-southeast-1 -a 828109562156
```

One of the issues of connecting to the standard deployment ECS is the ability to pull the image off ECR. To be able to
do that the EC2 instance one might have to follow one of the three approaches:

- use a public subnet for EC2
- complement with a NAT Gateway
- VPC Endpoint, ECS to ECR.

  With the second & third approach there is a small price ($35+ per month) which can be avoided for Non Prod
  Environments. Hence we use a public subnet with appropriate SecurityGroup restrictions in place for our Non-Prods. In
  production however, we will use a NAT Gateway.

## What to do on Non-Prod deployments?

After the cloudformation deployment - just make the below changes by hand.
```Note:``` You may need to revert these settings to apply a cloudformation change-set.

### Public IPv4 address in Subnet.

![](../misc/images/modify-auto-assign-ip-setting.png)

### Subnet View

![](../misc/images/subnet.jpg)

### Running the deployment from outside pipeline.

![](../misc/images/run.jpg)

# TODO

* Add right policy for the ECS role.
* Add a VPC Endpoint.
* chane to terraform