#!/bin/bash
ACCOUNT=828109562156
BUCKET_NAME="${ACCOUNT}-deployment-bucket"
TABLE_NAME="${ACCOUNT}_terraform_lock"
AWS_REGION="ap-southeast-1"
STAGE=dev

if aws s3api head-bucket --bucket "$BUCKET_NAME" 2>/dev/null; then
    echo "Bucket '$BUCKET_NAME' already exists, skipping creation."
else
    echo "Bucket '$BUCKET_NAME' does not exist. Creating..."
    aws s3 mb s3://"$BUCKET_NAME"
    echo "Bucket '$BUCKET_NAME' created."
fi

aws dynamodb describe-table --table-name "${TABLE_NAME}" --region "${AWS_REGION}" &> /dev/null
if [ $? -eq 0 ]; then
    echo "DynamoDB table ${TABLE_NAME} already exists, skipping creation."
else
    # Create DynamoDB table
    aws dynamodb create-table \
        --table-name "${TABLE_NAME}" \
        --attribute-definitions AttributeName="LockID",AttributeType="S" \
        --key-schema AttributeName="LockID",KeyType=HASH \
        --billing-mode PAY_PER_REQUEST \
        --region "${AWS_REGION}"
    echo "DynamoDB table ${TABLE_NAME} created."
fi

rm -rf .terraform

terraform init \
-backend-config="bucket=${BUCKET_NAME}" \
-backend-config="key=${STAGE}" \
-backend-config="region=${AWS_REGION}"

terraform apply -var="account=${ACCOUNT}" -auto-approve

