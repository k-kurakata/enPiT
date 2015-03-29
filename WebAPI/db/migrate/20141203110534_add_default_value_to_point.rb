class AddDefaultValueToPoint < ActiveRecord::Migration
  def change
    change_column_default :users, :point, 0
  end
end
