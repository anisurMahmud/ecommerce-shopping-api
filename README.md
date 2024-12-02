# ecommerce-shopping-api
# Shopping API

## Description

The Shopping API is a backend service for an eCommerce site, developed using Spring Boot. This API currently supports fundamental models and services, including products, categories, and images. It also features an exception handling layer for products, images, and categories, ensuring robust error management.

Recent updates include the addition of a cart and cart item functionality, featuring models, services, and repositories to manage cart operations. Key features include methods to add/remove items, clear the cart, and calculate the total price of items. A CartController and CartItemController have been implemented for handling cart-related requests. These improvements lay the foundation for future cart management and eCommerce functionality.

This project is in active development, with more features and enhancements planned for the future.

## Models

- **Product**: Represents the items available for sale.
- **Category**: Organizes products into different classifications.
- **Image**: Manages product images.
- **Cart**: Manages products Carts.
- **CartItem**: Manages cartItems in Cart.

## Features

- Basic CRUD operations for products, categories, images, cart and cartItems
- Exception handling for products to manage errors effectively
- can save product info like name, brand, description etc along with images.
- fetch data based on different criterias like brand, category and so on.
- Cart & CartItem Models: Added models for managing shopping carts and items.
- Service Layer: Introduced service classes for cart operations and business logic.
- Repository Integration: Repositories for Cart and CartItem to manage database interactions.
- Cart Management Methods: Added methods like addItemToCart, removeItemFromCart, and clearCart.
- Exception Handling: Enhanced error handling for cart operations.
- Controllers: Created CartController and CartItemController for API endpoints.
- Total Price Calculation: Implemented total price calculation for cart items.

## Installation

To set up the Shopping API locally, follow these steps:

1. **Clone the repository**:
   ```bash
   git clone https://github.com/anisurMahmud/ecommerce-shopping-api.git
