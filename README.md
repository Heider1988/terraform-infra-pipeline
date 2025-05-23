# Terraform Infra Pipeline

A Spring Boot application with Terraform infrastructure as code and GitHub Actions CI/CD pipeline for automated deployment to AWS.

## Project Overview

This project demonstrates a simple Spring Boot REST API with Terraform infrastructure as code for AWS resources. The application provides a time service endpoint that returns the current time in São Paulo, Brazil timezone.

The infrastructure is managed using Terraform and is deployed automatically to AWS using GitHub Actions workflows. The project supports both development and production environments.

## Technologies Used

- **Backend**:
  - Java 17
  - Spring Boot 3.4.6
  - Maven

- **Infrastructure as Code**:
  - Terraform 1.8.3
  - AWS S3 (for application resources and Terraform state)
  - AWS DynamoDB (for Terraform state locking)

- **CI/CD**:
  - GitHub Actions

## Project Structure

```
├── .github/workflows/       # GitHub Actions workflow definitions
│   ├── main.yml             # Production deployment workflow
│   └── terraform.yml        # Reusable Terraform workflow
├── infra/                   # Terraform infrastructure code
│   ├── envs/                # Environment-specific configurations
│   │   ├── dev/             # Development environment
│   │   └── prod/            # Production environment
│   ├── backend.tf           # Terraform backend configuration
│   ├── destroy_config.json  # Configuration for infrastructure destruction
│   ├── main.tf              # Main Terraform resources
│   ├── provider.tf          # AWS provider configuration
│   └── variables.tf         # Terraform variables
└── src/                     # Spring Boot application source code
    ├── main/
    │   ├── java/
    │   │   ├── com/api/terraform/pipeline/terraforminfrapipeline/
    │   │   │   └── TerraformInfraPipelineApplication.java  # Main application class
    │   │   ├── controller/
    │   │   │   └── TesteController.java                    # REST controller
    │   │   └── dto/
    │   │       └── response/
    │   │           └── TimeResponse.java                   # Response DTO
    │   └── resources/
    │       └── application.properties                      # Application configuration
    └── test/                # Test source code
```

## API Endpoints

- `GET /api/time`: Returns the current time in São Paulo, Brazil timezone in the format "dd/MM/yyyy HH:mm:ss"

Example response:
```json
{
  "time": "01/01/2023 12:00:00"
}
```

## Infrastructure

The project uses Terraform to manage AWS infrastructure:

- Creates an S3 bucket with a name based on the environment (dev or prod) and a random suffix
- Uses S3 for Terraform state storage
- Uses DynamoDB for state locking
- Deployed to the AWS sa-east-1 region (South America - São Paulo)

## CI/CD Pipeline

The project uses GitHub Actions for CI/CD:

- **Production Deployment**: Triggered on pushes to the main branch
- **Terraform Workflow**: A reusable workflow that handles Terraform operations:
  - Initializes Terraform with the appropriate backend configuration
  - Validates the Terraform configuration
  - Creates or selects the appropriate workspace based on the environment
  - Applies or destroys the infrastructure based on the configuration in `destroy_config.json`

## Setup and Installation

### Prerequisites

- Java 17 or higher
- Maven
- AWS account with appropriate permissions
- Terraform 1.8.3 or compatible version

### Local Development

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/Terraform-Infra-Pipeline.git
   cd Terraform-Infra-Pipeline
   ```

2. Build the application:
   ```bash
   mvn clean install
   ```

3. Run the application:
   ```bash
   mvn spring-boot:run
   ```

4. Access the API at http://localhost:8080/api/time

### Infrastructure Deployment

To deploy the infrastructure manually:

1. Navigate to the infra directory:
   ```bash
   cd infra
   ```

2. Initialize Terraform:
   ```bash
   terraform init \
     -backend-config="bucket=your-state-bucket" \
     -backend-config="key=Terraform-Infra-Pipeline" \
     -backend-config="region=sa-east-1" \
     -backend-config="dynamodb_table=your-lock-table"
   ```

3. Select or create a workspace:
   ```bash
   terraform workspace select dev || terraform workspace new dev
   ```

4. Apply the configuration:
   ```bash
   terraform apply -var-file="./envs/dev/terraform.tfvars"
   ```

## Infrastructure Destruction

To control whether infrastructure should be destroyed or applied, modify the `destroy_config.json` file. Set values to `true` to destroy the environment or `false` to apply/maintain the infrastructure:

```json
{
  "dev": false,
  "prod": false
}
```

## Contributing

1. Fork the repository
2. Create a feature branch: `git checkout -b feature/your-feature-name`
3. Commit your changes: `git commit -am 'Add some feature'`
4. Push to the branch: `git push origin feature/your-feature-name`
5. Submit a pull request

## License

This project is licensed under the MIT License - see the LICENSE file for details.
