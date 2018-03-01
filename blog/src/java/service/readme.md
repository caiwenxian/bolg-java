(1)添加事务方法：
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = SerException.class)