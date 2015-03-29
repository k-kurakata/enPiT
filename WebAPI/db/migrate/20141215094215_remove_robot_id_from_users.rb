class RemoveRobotIdFromUsers < ActiveRecord::Migration
  def change
    remove_column :users, :robot_id, :string
    add_column :users, :robot_pass, :string
  end
end
