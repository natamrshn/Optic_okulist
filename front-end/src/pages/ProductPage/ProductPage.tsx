import React from "react";
import { useNavigate, useParams } from "react-router-dom";
import Container from "../../components/ui/Container/Container";
import "./ProductPage.scss";
import { prodsWithIdsIds } from "../../components/ShoppingCart/ShoppingCart";

const ProductPage = () => {
    const navigate = useNavigate()
    const params = useParams();
    
    const product = prodsWithIdsIds.find(prod => prod.id === params.id);

    console.log(params.id)
    return (
        <main style={{marginBlock: "72px"}}>
            <Container>
                <section>
                    {product ? (
                        <div className="productPage">
                            <button className="productPage-button" onClick={() => navigate(-1)}>go back</button>
                            <div className="productPage-wrapper">
                                <img src={product.imgUrl} className="productPage-img" alt="img" />
                                <div>
                                    <h2 className="productPage-name">{product.name}</h2>
                                    <p>${product.price}</p>
                                    <p>{product.description}</p>
                                </div>
                            </div>
                        </div>
                    ) : (
                        <div>loading.....</div>
                    )}
                </section>
            </Container>
        </main>
    )
};

export default ProductPage;
