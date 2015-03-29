class ProductsController < ApplicationController
  before_action :set_product, only: [:show, :edit, :update, :destroy, :purchase, :document]
  before_action :authenticate_user!

  # GET /products
  # GET /products.json
  def index
    @products = Product.all
  end

  # GET /products/1
  # GET /products/1.json
  def show
  end

  # GET /products/new
  def new
    @product = Product.new
  end

  # GET /products/1/edit
  def edit
  end

  # POST /products
  # POST /products.json
  def create
    @product = Product.new(product_params)

    respond_to do |format|
      if @product.save
        format.html { redirect_to @product, notice: 'Product was successfully created.' }
        format.json { render :show, status: :created, location: @product }
      else
        format.html { render :new }
        format.json { render json: @product.errors, status: :unprocessable_entity }
      end
    end
  end

  # PATCH/PUT /products/1
  # PATCH/PUT /products/1.json
  def update
    respond_to do |format|
      if @product.update(product_params)
        format.html { redirect_to @product, notice: 'Product was successfully updated.' }
        format.json { render :show, status: :ok, location: @product }
      else
        format.html { render :edit }
        format.json { render json: @product.errors, status: :unprocessable_entity }
      end
    end
  end

  # DELETE /products/1
  # DELETE /products/1.json
  def destroy
    @product.destroy
    respond_to do |format|
      format.html { redirect_to products_url, notice: 'Product was successfully destroyed.' }
      format.json { head :no_content }
    end
  end

  # POST /products/1/purchase
  def purchase
    params = { :user_id => @user.id, :product_id => @product.id }
    @purchased_product = PurchasedProduct.new(params)
    @purchased_product.save
    @user.update({ :point => @user.point - @product.price })
    respond_to do |format|
      format.html { redirect_to products_url, notice: 'You got the product!' }
      format.json { head :no_content }
    end
    if @product.document == false
      send_feature_change_token(@user.name,@product.feature_change_token)
    end
  end

  # GET /products/1/document
  def document
    if not @user.purchased_products.purchased? @product
      respond_to do |format|
        format.html { redirect_to products_url, notice: 'You don\'t have enough permission to see the document.' }
        format.json { head :no_content }
      end
    end
  end

  # POST /products/send_change_token
  def send_change_token
    logger.debug(params[:user_name])
    logger.debug(params[:token])
    send_feature_change_token(params[:user_name],params[:token]);
    redirect_to products_url
  end
  
  private
    # Use callbacks to share common setup or constraints between actions.
    def set_product
      @product = Product.find(params[:id])
    end

    # Never trust parameters from the scary internet, only allow the white list through.
    def product_params
      params.require(:product).permit(:name, :price)
    end
    def send_feature_change_token(user_name, token)
      url_params = URI.encode_www_form([["id", user_name], ["contentsdata", token]])
      uri = URI.parse("http://54.65.61.46:8080/ServiceSample/contentsupload?#{url_params}")
      res = Net::HTTP.get_response(uri)
      logger.debug("uri = #{uri}")
      logger.debug("res = #{res}")
      return res
    end
end
