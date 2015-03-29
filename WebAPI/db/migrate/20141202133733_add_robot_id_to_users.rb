class AddRobotIdToUsers < ActiveRecord::Migration
  def change
    add_column :users, :robot_id, :string
    add_index :users, :robot_id, unique: true
    add_index :users, :name, unique: true
  end
end
