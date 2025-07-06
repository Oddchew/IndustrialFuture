package by.oddchew.IndustrialFuture.blocks;

import by.oddchew.IndustrialFuture.blockentity.CableBlockEntity;
import by.oddchew.IndustrialFuture.blockentity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraftforge.common.capabilities.ForgeCapabilities;

import javax.annotation.Nullable;

public class CableBlock extends Block implements EntityBlock {
    public static final BooleanProperty CONNECTED_NORTH = BooleanProperty.create("connected_north");
    public static final BooleanProperty CONNECTED_SOUTH = BooleanProperty.create("connected_south");
    public static final BooleanProperty CONNECTED_EAST = BooleanProperty.create("connected_east");
    public static final BooleanProperty CONNECTED_WEST = BooleanProperty.create("connected_west");
    public static final BooleanProperty CONNECTED_UP = BooleanProperty.create("connected_up");
    public static final BooleanProperty CONNECTED_DOWN = BooleanProperty.create("connected_down");

    private final int maxEnergyPerReceiver;

    // Определяем VoxelShape для ядра (6x6x6 пикселей) и соединений
    private static final VoxelShape CORE_SHAPE = Shapes.box(0.3125, 0.3125, 0.3125, 0.6875, 0.6875, 0.6875);
    private static final VoxelShape NORTH_SHAPE = Shapes.box(0.3125, 0.3125, 0.0, 0.6875, 0.6875, 0.3125);
    private static final VoxelShape SOUTH_SHAPE = Shapes.box(0.3125, 0.3125, 0.6875, 0.6875, 0.6875, 1.0);
    private static final VoxelShape EAST_SHAPE = Shapes.box(0.6875, 0.3125, 0.3125, 1.0, 0.6875, 0.6875);
    private static final VoxelShape WEST_SHAPE = Shapes.box(0.0, 0.3125, 0.3125, 0.3125, 0.6875, 0.6875);
    private static final VoxelShape UP_SHAPE = Shapes.box(0.3125, 0.6875, 0.3125, 0.6875, 1.0, 0.6875);
    private static final VoxelShape DOWN_SHAPE = Shapes.box(0.3125, 0.0, 0.3125, 0.6875, 0.3125, 0.6875);

    public CableBlock(Properties properties, int maxEnergyPerReceiver) {
        super(properties);
        this.maxEnergyPerReceiver = maxEnergyPerReceiver;
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(CONNECTED_NORTH, false)
                .setValue(CONNECTED_SOUTH, false)
                .setValue(CONNECTED_EAST, false)
                .setValue(CONNECTED_WEST, false)
                .setValue(CONNECTED_UP, false)
                .setValue(CONNECTED_DOWN, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(CONNECTED_NORTH, CONNECTED_SOUTH, CONNECTED_EAST, CONNECTED_WEST, CONNECTED_UP, CONNECTED_DOWN);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return getConnectionState(context.getLevel(), context.getClickedPos());
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        return getConnectionState(level, pos);
    }

    private BlockState getConnectionState(BlockGetter level, BlockPos pos) {
        BlockState state = this.defaultBlockState();
        for (Direction direction : Direction.values()) {
            BlockPos neighborPos = pos.relative(direction);
            BlockState neighborState = level.getBlockState(neighborPos);
            boolean canConnect = neighborState.getBlock() instanceof CableBlock ||
                    (level.getBlockEntity(neighborPos) != null &&
                            level.getBlockEntity(neighborPos).getCapability(ForgeCapabilities.ENERGY, direction.getOpposite()).isPresent());
            state = state.setValue(getPropertyForDirection(direction), canConnect);
        }
        return state;
    }

    private static BooleanProperty getPropertyForDirection(Direction direction) {
        switch (direction) {
            case NORTH: return CONNECTED_NORTH;
            case SOUTH: return CONNECTED_SOUTH;
            case EAST: return CONNECTED_EAST;
            case WEST: return CONNECTED_WEST;
            case UP: return CONNECTED_UP;
            case DOWN: return CONNECTED_DOWN;
            default: throw new IllegalArgumentException("Invalid direction");
        }
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        VoxelShape shape = CORE_SHAPE;
        if (state.getValue(CONNECTED_NORTH)) shape = Shapes.or(shape, NORTH_SHAPE);
        if (state.getValue(CONNECTED_SOUTH)) shape = Shapes.or(shape, SOUTH_SHAPE);
        if (state.getValue(CONNECTED_EAST)) shape = Shapes.or(shape, EAST_SHAPE);
        if (state.getValue(CONNECTED_WEST)) shape = Shapes.or(shape, WEST_SHAPE);
        if (state.getValue(CONNECTED_UP)) shape = Shapes.or(shape, UP_SHAPE);
        if (state.getValue(CONNECTED_DOWN)) shape = Shapes.or(shape, DOWN_SHAPE);
        return shape;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new CableBlockEntity(pos, state, maxEnergyPerReceiver);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return level.isClientSide ? null : (world, pos, blockState, blockEntity) -> {
            if (blockEntity instanceof CableBlockEntity cable) {
                cable.tick();
            }
        };
    }
}